import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

Mobile.comment('--- BẮT ĐẦU: THẢ TIM ---')

// Gọi Keyword React (Đã có logic chờ Object bên trong Keyword)
CustomKeywords.'familier.common.PostKeywords.reactToLatestPost'()

Mobile.comment('Success: Heart reaction applied to the latest post.')