package com.cg.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.ExceptionResponse.CustomException;
import com.cg.model.Invoice;
import com.cg.model.PackageType;
import com.cg.model.Payment;
import com.cg.model.PaymentType;
import com.cg.repository.InvoiceRepository;
import com.cg.repository.PackageRepository;

@Component
public class InvoiceServiceImp implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private PackageRepository packageRepository;

	public Invoice genrateInvoice(Payment payment, String carId, String getAddOn, String washId)
			throws CustomException {
		Invoice invoice = new Invoice();

		invoice.setWashid(washId);
		System.out.println(washId);
		invoice.setCustomerId(payment.getCustomerId());
		invoice.setPayment(payment);
		invoice.setDate(LocalDate.now());
		invoice.setAddOn(getAddOn);
		
		invoice.setTotalamount(payment.getAmount());

		Invoice save = invoiceRepository.save(invoice);

		if (save != null) {
			return save;
		} else {
			throw new CustomException("Some thing went worng.please try again.");
		}

	}

	@Override
	public List<Invoice> getInvoicesByCustomerId(String id) throws CustomException {
		List<Invoice> all = invoiceRepository.findAll();
		List<Invoice> list = all.stream().filter(t -> t.getCustomerId().equals(id))
				.sorted((o1, o2) -> o2.getPayment().getDate().compareTo(o1.getPayment().getDate())).toList();
		if (list.isEmpty()) {
			throw new CustomException("No bills finds");
		}
		return list;
	}

	@Override
	public Invoice getLatestBill(String id) throws CustomException {
		List<Invoice> all = invoiceRepository.findAll();
		Optional<Invoice> first = all.stream().filter(p -> p.getCustomerId().equals(id))
				.sorted((o1, o2) -> o2.getPayment().getDate().compareTo(o1.getPayment().getDate())).findFirst();
		if (first.isEmpty()) {
			throw new CustomException("NO BILLS AVAILABE");
		}
		return first.get();
	}

	@Override
	public List<Invoice> getAllInvoices() throws CustomException {
		List<Invoice> all = invoiceRepository.findAll();
		all.sort((o1, o2) -> o2.getPayment().getDate().compareTo(o1.getPayment().getDate()));
		if (all.isEmpty()) {
			throw new CustomException("NO BILLS AVAILABE");
		}
		return all;
	}

	@Override
	public double calculateAmountByDate(String date) throws CustomException {

		LocalDate parsedDate;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			parsedDate = LocalDate.parse(date);
		} catch (Exception e) {
			throw new CustomException("Invalid date format. Please use the format yyyy-MM-dd.");
		}

		List<Invoice> all = invoiceRepository.findAll();
		double sum = all.stream().filter(t -> t.getDate().isAfter(parsedDate))
				.sorted((o1, o2) -> o2.getPayment().getDate().compareTo(o1.getPayment().getDate()))
				.mapToDouble(value -> value.getTotalamount()).sum();

		if (sum == 0) {
			throw new CustomException("No payments have been made after " + parsedDate.toString() + ".");
		}

		return sum;
	}

}
