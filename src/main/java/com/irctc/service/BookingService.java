package com.irctc.service;

import com.irctc.BookingRequest;
import com.irctc.BookingResponse;
import com.irctc.TrainBookingServiceGrpc.TrainBookingServiceImplBase;
import com.irctc.TransferStatus;

import io.grpc.stub.StreamObserver;

public class BookingService extends TrainBookingServiceImplBase {

	@Override
	public void bookTrain(BookingRequest request, StreamObserver<BookingResponse> responseObserver) {
		
		
		// Save to DB and generate a unique PNR number.
		
		
		BookingResponse bookingResponse = BookingResponse.newBuilder()
												.setPnr(100)
												.setStatus(TransferStatus.CONFIRMED)
												.build();
		
		responseObserver.onNext(bookingResponse);
		responseObserver.onCompleted();
		
	}
	
}
