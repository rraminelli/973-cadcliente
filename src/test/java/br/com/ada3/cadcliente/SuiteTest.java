package br.com.ada3.cadcliente;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
//@SelectPackages({"br.com.ada3.cadcliente"})
@SelectClasses({CadClienteRestControllerTest.class, CadClienteServiceTest.class} )
public class SuiteTest {
}
