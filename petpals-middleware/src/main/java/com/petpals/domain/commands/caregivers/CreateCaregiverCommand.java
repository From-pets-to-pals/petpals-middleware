package com.petpals.domain.commands.caregivers;

import com.petpals.clients.dto.caregivers.Days;
import com.petpals.shared.model.enums.PalsFriendsTypes;
import com.petpals.shared.model.enums.SpeciesEnum;

import java.util.Arrays;
import java.util.Objects;

public class CreateCaregiverCommand {
	private String reference;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	
	private String city;
	private String zipCode;
	private String country;
	private Days[] workingDays;
	private SpeciesEnum[] palsHandled;
	private boolean homeService;
	private double appointmentDuration;
	
	private PalsFriendsTypes caregiverType;
	private boolean isSubscribed;
	private double serviceRating;
	private double priceRating;
	
	public CreateCaregiverCommand() {
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreateCaregiverCommand that = (CreateCaregiverCommand) o;
		return homeService == that.homeService && Double.compare(appointmentDuration, that.appointmentDuration) == 0 && isSubscribed == that.isSubscribed && Double.compare(serviceRating, that.serviceRating) == 0 && Double.compare(priceRating, that.priceRating) == 0 && Objects.equals(reference, that.reference) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(zipCode, that.zipCode) && Objects.equals(country, that.country) && Objects.deepEquals(workingDays, that.workingDays) && Objects.deepEquals(palsHandled, that.palsHandled) && caregiverType == that.caregiverType;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(reference, firstName, lastName, email, phoneNumber, address, city, zipCode, country, Arrays.hashCode(workingDays), Arrays.hashCode(palsHandled), homeService, appointmentDuration, caregiverType, isSubscribed, serviceRating, priceRating);
	}
	
	@Override
	public String toString() {
		return "CreateCaregiverCommand{" +
					   "reference='" + reference + '\'' +
					   ", firstName='" + firstName + '\'' +
					   ", lastName='" + lastName + '\'' +
					   ", email='" + email + '\'' +
					   ", phoneNumber='" + phoneNumber + '\'' +
					   ", address='" + address + '\'' +
					   ", city='" + city + '\'' +
					   ", zipCode='" + zipCode + '\'' +
					   ", country='" + country + '\'' +
					   ", workingDays=" + Arrays.toString(workingDays) +
					   ", palsHandled=" + Arrays.toString(palsHandled) +
					   ", homeService=" + homeService +
					   ", appointmentDuration=" + appointmentDuration +
					   ", caregiverType=" + caregiverType +
					   ", isSubscribed=" + isSubscribed +
					   ", serviceRating=" + serviceRating +
					   ", priceRating=" + priceRating +
					   '}';
	}
	
	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Days[] getWorkingDays() {
		return workingDays;
	}
	
	public void setWorkingDays(Days[] workingDays) {
		this.workingDays = workingDays;
	}
	
	public SpeciesEnum[] getPalsHandled() {
		return palsHandled;
	}
	
	public void setPalsHandled(SpeciesEnum[] palsHandled) {
		this.palsHandled = palsHandled;
	}
	
	public boolean isHomeService() {
		return homeService;
	}
	
	public void setHomeService(boolean homeService) {
		this.homeService = homeService;
	}
	
	public double getAppointmentDuration() {
		return appointmentDuration;
	}
	
	public void setAppointmentDuration(double appointmentDuration) {
		this.appointmentDuration = appointmentDuration;
	}
	
	public PalsFriendsTypes getCaregiverType() {
		return caregiverType;
	}
	
	public void setCaregiverType(PalsFriendsTypes caregiverType) {
		this.caregiverType = caregiverType;
	}
	
	public boolean isSubscribed() {
		return isSubscribed;
	}
	
	public void setSubscribed(boolean subscribed) {
		isSubscribed = subscribed;
	}
	
	public double getServiceRating() {
		return serviceRating;
	}
	
	public void setServiceRating(double serviceRating) {
		this.serviceRating = serviceRating;
	}
	
	public double getPriceRating() {
		return priceRating;
	}
	
	public void setPriceRating(double priceRating) {
		this.priceRating = priceRating;
	}
}
