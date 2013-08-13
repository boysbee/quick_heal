class QuickHeal {
	def findJobListByDiscountCode(discountCode : String) : Array[Array[Any]] = {
		return Array[Array[Any]](List("CSADDCSEGMENT","TTHP-966BGE","DIR040","Thitaree Thongnamsap","Nattaporn Chatmalairut").toArray,
		Array("CSDCMNPO2R","SNGK-94U6HP", "DIR040","Surisara Ngamtragoonsuk","Nattaporn Chatmalairut").toArray,
		Array("CSDCMNPO2R","SNGK-94U6HP","DIR042","Surisara Ngamtragoonsuk","Nattaporn Chatmalairut").toArray)
	}

	def findJobListByJobName(jobName : String) : Array[Array[Any]] = {
		return Array[Array[Any]](List("CSADDCSEGMENT","TTHP-966BGE","DIR040","Thitaree Thongnamsap","Nattaporn Chatmalairut").toArray,
		Array("CSDCMNPO2R","SNGK-94U6HP", "DIR040","Surisara Ngamtragoonsuk","Nattaporn Chatmalairut").toArray,
		Array("CSDCMNPO2R","SNGK-94U6HP","DIR042","Surisara Ngamtragoonsuk","Nattaporn Chatmalairut").toArray)
	}
}