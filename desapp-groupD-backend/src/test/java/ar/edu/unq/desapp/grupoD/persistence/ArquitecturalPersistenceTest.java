package ar.edu.unq.desapp.grupoD.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * This test class verifies that all the application entities are well
 * configured so they can be saved, retrieved, and deleted from the database.
 * It's generic enough so it doesn't rely on modifications for every new entity.
 * 
 * @author Lucas
 *
 */
@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class ArquitecturalPersistenceTest extends AbstractTransactionalJUnit4SpringContextTests {

	private static final String ENTITIES_PACKAGE_NAME = "ar.edu.unq.desapp.grupoD.model"; 
	private static final String BUILDERS_PACKAGE_NAME = "ar.edu.unq.desapp.grupoD.model.builders"; 
	
	@Autowired
	private ObjectDao dao;
	
	public void setDao(ObjectDao dao) {
		this.dao = dao;
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testEntities() throws Exception {
		Class[] entities = getClasses(ENTITIES_PACKAGE_NAME);
		for(Class entityClass : entities){
			if(! Modifier.isAbstract( entityClass.getModifiers() ) ){
				Object entityInstance = getEntity(entityClass.getSimpleName());
				checkPersistency( entityInstance );
			}
		}
	}
	
	private void checkPersistency(Object entity) throws Exception{
		dao.save(entity);
		List<Object> allEntities = dao.findAll(entity.getClass());
		assertFalse(allEntities.isEmpty());
		assertEquals(1 , allEntities.size() );
		assertEquals(entity , allEntities.get(0));
		dao.delete(entity);
		allEntities = dao.findAll();
		assertTrue(allEntities.isEmpty());
	}

	private static Object getEntity(String className) throws Exception{
		Object builder = getBuilder(className);
		Method method = builder.getClass().getMethod("any");
		return method.invoke(builder, null);
	}

	/**
	 * Returns a builder given an associated entity name.
	 * By convention, every builder has to be called: "{entityName}Builder"
	 * For example: given the entity "Person", the builder must be called "PersonBuilder" 
	 * @param className of the entity to infer the builder name
	 * @return A Builder for the given entity
	 * @throws Exception 
	 */
	private static Object getBuilder(String className) throws Exception{
		String builderClassName = BUILDERS_PACKAGE_NAME + "." + className + "Builder";
		Class<?> clazz = Class.forName(builderClassName);
		Constructor<?> constructor = clazz.getConstructor();
		return constructor.newInstance();
	}
	
	
	private static List<Class> findClasses(File directory, String packageName)	throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class") && 
					   !file.getName().endsWith("Test.class") && 
					   !file.getName().endsWith("Builder.class") &&
					   !file.getName().contains("BankTransfer.class") &&
					   !file.getName().contains("CreditCard.class") &&
					   !file.getName().contains("PettyCash.class") &&
					   !file.getName().contains("DebitCard.class") &&
					   !file.getName().contains("AccountsState") &&
					   !file.getName().contains("ExcelReader.class")
					) {
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

	private static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

}
