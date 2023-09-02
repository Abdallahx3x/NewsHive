package com.example.entities

open class NewsHiveException(message:String?): Exception(message)
class NullDataException(message: String):NewsHiveException(message)
open class NetworkException(message: String?):NewsHiveException(message)
class RateLimitExceededException(message: String?):NetworkException(message)
class NoInternetException(message: String?):NetworkException(message)
class UnAuthorizedException(message: String?):NewsHiveException(message)

