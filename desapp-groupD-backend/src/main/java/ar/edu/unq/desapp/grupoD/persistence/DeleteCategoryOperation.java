package ar.edu.unq.desapp.grupoD.persistence;

public class DeleteCategoryOperation extends SaveOrUpdateCategoryOperation {

	private HibernateManager manager;
	private String categoryName;

	protected void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setManager(HibernateManager manager) {
		this.manager = manager;
	}

	@Override
	public void execute() {
		String hql = "delete from Category where categoryName= :categoryName";
		manager.getSession().createQuery(hql)
				.setString("categoryName", categoryName).executeUpdate();
	}

}
