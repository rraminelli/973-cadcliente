package br.com.ada3.cadcliente;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
//@SelectPackages({"br.com.ada3.cadcliente"})
@SelectClasses({CadClienteControllerTest.class, CadClienteServiceTest.class} )
public class SuiteTest {
}
