package test_case;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Page_Objects.Access_Permissions_page;
import base.Base_Class;

public class Access_permissions extends Base_Class{
	
	private static  Logger log = LogManager.getLogger(Access_permissions.class);
	
	Access_Permissions_page accessobj;
	
	@BeforeClass
	public void Access_Permissions_Setup()
	{
		log.info("Access Permissions link Click");
		accessobj = new Access_Permissions_page(obj);
	}
	
	@Test(priority=1)
	public void Add_Group()
	{
		log.info("This is Add Group Method");
		
		 accessobj.addgroup();
	}
	@Test(priority=2)
	public void Add_Role() throws InterruptedException
	{
		log.info("This is Add Role Method");
		
		 accessobj.addrole();
	}

}
