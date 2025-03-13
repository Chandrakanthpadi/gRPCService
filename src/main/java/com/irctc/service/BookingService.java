package com.irctc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.irctc.BookingRequest;
import com.irctc.BookingResponse;
import com.irctc.Date;
import com.irctc.TrainBookingServiceGrpc.TrainBookingServiceImplBase;
import com.irctc.TransferStatus;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class BookingService extends TrainBookingServiceImplBase {

	@Override
	public void bookTrain(BookingRequest request, StreamObserver<BookingResponse> responseObserver) {

		/*
		 * Need to validate and parse the data.If not valid throw bad request;
		 * 
		 */

		if (!validateRequest(request)) {
			responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Bad Request").asRuntimeException());
			return;
		}

		try {
			
			java.util.Date travelDate = parseDate(request.getTravelDate());
			
		} catch (ParseException e) {
			responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid Date").asRuntimeException());
			return;
		}

		/*
		 * If valid save to DB and generate a unique PNR number.
		 */
		
		BookingResponse bookingResponse = BookingResponse.newBuilder().setPnr(200).setStatus(TransferStatus.CONFIRMED)
				.build();

		responseObserver.onNext(bookingResponse);
		responseObserver.onCompleted();

	}

	private boolean validateRequest(BookingRequest request) {

		if (request.getTrainId() < 0) {
			return false;
		}
		return true;

	}

	private java.util.Date parseDate(Date travelDateRequest) throws ParseException {

		String day = travelDateRequest.getDay();
		String month = travelDateRequest.getMonth();
		String year = travelDateRequest.getYear();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date travelDate = simpleDateFormat.parse(day + "/" + month + "/" + year);
		
		return travelDate;

	}

}
