package com.irctc;

import com.irctc.service.BookingService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class TrainBookingApplication {

	public static void main(String[] args) throws Exception {
		
		Server server = ServerBuilder.forPort(9000).addService(new BookingService()).build();
		
		server.start();
		server.awaitTermination();

	}

}
