package com.HabibDev.BookShopApplication.exception.custom;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(String message){super(message);}
}