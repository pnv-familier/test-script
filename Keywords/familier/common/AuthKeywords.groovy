package familier.common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable


public class AuthKeywords {
	@Keyword
	def loginWithProfile() {
		Mobile.comment("Step: Login using Profile credentials")
		Mobile.waitForElementPresent(findTestObject('Object Repository/Login/txt_Email'), 15)
		Mobile.setText(findTestObject('Object Repository/Login/txt_Email'), GlobalVariable.email, 10)
		Mobile.setText(findTestObject('Object Repository/Login/txt_Password'), GlobalVariable.password, 10)
		Mobile.tap(findTestObject('Object Repository/Login/btn_Login'), 10)
	}
	
	@Keyword
	def logoutFromFamilier() {
		Mobile.comment('Step: Opening Profile to Logout')
		
		// 1. Click Profile Icon
		Mobile.tap(findTestObject('Object Repository/FamilySocial/Logout/btn_ProfileIcon'), 10)
		
		// 2. Click Logout Button
		Mobile.comment('Step: Clicking Logout button')
		Mobile.tap(findTestObject('Object Repository/FamilySocial/Logout/btn_Logout'), 10)
		
		// 3. Validation
		Mobile.comment('Step: Verifying redirection to Login screen')
		Mobile.waitForElementPresent(findTestObject('Object Repository/Login/btn_Login'), 15)
		
		Mobile.comment('Success: Logged out successfully.')
	}
}
