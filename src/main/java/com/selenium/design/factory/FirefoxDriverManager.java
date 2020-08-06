package com.selenium.design.factory;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverManager extends DriverManager {

	private GeckoDriverService ffService;

	@Override
	protected void startService() {
		if (null == ffService) {
			try {
				ffService= new GeckoDriverService.Builder()
						.usingDriverExecutable(new File(System.getProperty("user.dir") + "/exe/geckodriver.exe"))
						.usingAnyFreePort().build();

				ffService.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void stopService() {
		if (null != ffService && ffService.isRunning()) {
			ffService.stop();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void createDriver() {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("test-type");
		capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS,options);
		driver = new FirefoxDriver(ffService, options);

	}
}
