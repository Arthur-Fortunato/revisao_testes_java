package br.com.infnet;

import com.github.javafaker.Faker;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Faker faker = new Faker();

        for (int i = 0; i < 10; i++){
            System.out.println(faker.name().firstName());
            System.out.println(faker.internet().emailAddress());
        }
    }
}
