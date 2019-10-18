package main.java.Almacen.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;




public class SpringInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {

	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { SpringappBusinessConfig.class };
	}

	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { SpringappWebConfig.class };
	}

	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
