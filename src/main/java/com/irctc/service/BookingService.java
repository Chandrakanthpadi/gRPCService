package com.irctc.service;

import com.irctc.BookingRequest;
import com.irctc.BookingResponse;
import com.irctc.TrainBookingServiceGrpc.TrainBookingServiceImplBase;

import io.grpc.stub.StreamObserver;

public class BookingService extends TrainBookingServiceImplBase {

	@Override
	public void bookTrain(BookingRequest request, StreamObserver<BookingResponse> responseObserver) {
	
		BookingResponse bookingResponse = BookingResponse.newBuilder().setPnr(0).build();
		responseObserver.onNext(bookingResponse);
		responseObserver.onCompleted();
		
	}
	
}
