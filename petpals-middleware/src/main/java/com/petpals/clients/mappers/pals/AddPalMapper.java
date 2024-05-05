package com.petpals.clients.mappers.pals;

import com.petpals.clients.dto.pals.AddFirstPal;
import com.petpals.domain.commands.pals.CreatePalCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface AddPalMapper {
	// Common
	@Mapping(source = "reference", target = "reference")
	
	// Identity informations
	
	@Mapping(source = "palIdentityInformation.name", target = "name")
	@Mapping(source = "palIdentityInformation.shortname", target = "shortname")
	@Mapping(source = "palIdentityInformation.birthDate", target = "birthDate")
	@Mapping(source = "palIdentityInformation.isMale", target = "isMale")
	@Mapping(source = "palIdentityInformation.specie", target = "specie")
	@Mapping(source = "palIdentityInformation.breed", target = "breed")
	@Mapping(source = "palIdentityInformation.icadIdentifier", target = "icadIdentifier")
	@Mapping(source = "palIdentityInformation.hasPassport", target = "hasPassport")
	
	// Medical informations
	
	@Mapping(source = "palMedicalInformation.isVaccinated", target = "isVaccinated")
	@Mapping(source = "palMedicalInformation.medicalHistory", target = "medicalHistory")
	@Mapping(source = "palMedicalInformation.nextVaccine", target = "nextVaccine")
	@Mapping(source = "palMedicalInformation.nextPlannedVetApp", target = "nextPlannedVetApp")
	@Mapping(source = "palMedicalInformation.isSterilized", target = "isSterilized")
	
	// Measurement informations
	
	@Mapping(source = "palMeasurement.weight", target = "weight")
	@Mapping(source = "palMeasurement.height", target = "height")
	AddFirstPal fromDomain(CreatePalCommand createPalCommand);
}
