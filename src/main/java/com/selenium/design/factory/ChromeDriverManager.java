package com.selenium.design.factory;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverManager extends DriverManager {

	private ChromeDriverService chService;

	@Override
	protected void startService() {
		if (null == chService) {
			try {
				chService = new ChromeDriverService.Builder()
						.usingDriverExecutable(new File(System.getProperty("user.dir") + "/exe/chromedriver_84.exe"))
						.usingAnyFreePort().build();

				chService.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void stopService() {
		if (null != chService && chService.isRunning()) {
			chService.stop();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void createDriver() {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(chService, capabilities);

	}
}
