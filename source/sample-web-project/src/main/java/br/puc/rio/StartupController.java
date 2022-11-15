package br.puc.rio;

import java.io.FileNotFoundException;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.puc.rio.controller.UpgradeConfigurationController;

@Resource
public class StartupController {

    @Path("/")
    public void init() throws FileNotFoundException {
    	UpgradeConfigurationController.execute();
    }

}
