package com.petpals;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Middleware {
	
	public static void main(String ... args) {
		Quarkus.run(args);
	}
}
