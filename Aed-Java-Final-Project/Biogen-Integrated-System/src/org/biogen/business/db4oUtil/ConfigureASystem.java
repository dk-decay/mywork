package org.biogen.business.db4oUtil;

import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import java.util.List;
import java.util.NavigableMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.biogen.business.enterprise.model.BusinessUnit;
import org.biogen.business.enterprise.model.BusinessUnitType;
import org.biogen.business.medicare.model.Disease;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.ecosystem.model.Organization;
import org.biogen.network.model.OperationalRegion;
import org.biogen.network.model.OperationalRegionType;
import org.biogen.user.model.BusinessUser;
import org.biogen.user.model.RoleType;
import org.biogen.user.model.UserType;

/**
 *
 * @author rrheg
 */
public class ConfigureASystem {

    public static Ecosystem configure() {

        Ecosystem system = Ecosystem.getInstance();

        // getBusiness user in place
        saveBusinessUser(system);
        createNetworks(system, 1);
        uploadDiseaseData(system);

        return system;
    }

    public static void uploadDiseaseData(Ecosystem system) {

        MasterConfiguration instance = MasterConfiguration.getInstance();
        NavigableMap<String, List<String>> diseaseData = instance.getDiseaseData();
        diseaseData.forEach((k, v) -> {

            Disease disease = system.getDiseaseStudyDirectory().addNewDisease();
            disease.setName(k);
            disease.setDescription("This disease is called " + k);
            v.forEach(a -> disease.addAffectedGenes(a));

        });

    }

    public static void saveBusinessUser(Ecosystem system) {
        system.getUserDirectory().addUser(UserType.BU,
                BusinessUnitType.BUSINESS, RoleType.BUS_ADMIN, "bu", "bu");

    }

    public static void createNetworks(Ecosystem system, int counter) {
        OperationalRegionType[] regionArr = {OperationalRegionType.ASIA, OperationalRegionType.EUROPE,
            OperationalRegionType.NORTH_AMERICA, OperationalRegionType.SOUTH_AMERICA};

        for (OperationalRegionType r : regionArr) {

            OperationalRegion region = system
                    .getOpRegionDirectory()
                    .addOperationalRegion(r);

            //network admin user
            BusinessUser networkAdminUser = (BusinessUser) system.getUserDirectory()
                    .addUser(UserType.BU, BusinessUnitType.NETWORK,
                            RoleType.NETWORK_ADMIN, "na" + "_" + r, "na" + "_" + r);

            networkAdminUser.setNetworkId(region.getRegionId());
            networkAdminUser.setFirstname(networkAdminUser.getUsername());
            networkAdminUser.setLastname("");
            createEnterprise(system, region, counter);

        }

    }

    public static void createEnterprise(Ecosystem system, OperationalRegion region, int counter) {

        BusinessUnit chemEnterprise = region.getBuDir()
                .addBusinessUnit(BusinessUnitType.CHEMICAL,
                        region.getRegionId());

        BusinessUnit drugEnterprise = region
                .getBuDir()
                .addBusinessUnit(BusinessUnitType.DRUG, region.getRegionId());

        BusinessUnit medicareEnterprise = region.getBuDir()
                .addBusinessUnit(BusinessUnitType.MEDICARE,
                        region.getRegionId());

        createEnterpriseAdmin(system, region, chemEnterprise, BusinessUnitType.CHEMICAL, counter);
        createEnterpriseAdmin(system, region, drugEnterprise, BusinessUnitType.DRUG, counter);
        createEnterpriseAdmin(system, region, medicareEnterprise, BusinessUnitType.MEDICARE, counter);

        createChemicalOrganization(system, region, chemEnterprise, counter);
        createDrugOrganization(system, region, drugEnterprise, counter);
        createHospitalOrganization(system, region, medicareEnterprise, counter);

    }

    public static void createEnterpriseAdmin(Ecosystem system, OperationalRegion region, BusinessUnit enterprise, BusinessUnitType type, int counter) {
        BusinessUser enterpriseAdminUser = (BusinessUser) system.getUserDirectory()
                .addUser(UserType.BU, type,
                        RoleType.ENTERPRISE_ADMIN,
                        region.getType().toString()
                        + "_" + enterprise.getUnitType().toString(),
                        "123");

        region.getUserList().add(enterpriseAdminUser);
        enterprise.getUserList().add(enterpriseAdminUser);
        enterpriseAdminUser.setFirstname(enterpriseAdminUser.getUsername());
        enterpriseAdminUser.setLastname("");
        enterpriseAdminUser.setEnterpriseId(enterprise.getEnterpriseId());
        enterpriseAdminUser.setNetworkId(region.getRegionId());
        enterpriseAdminUser.setFirstname(enterpriseAdminUser.getUsername());
        enterpriseAdminUser.setLastname(enterprise.getUnitType().toString());

    }

    public static void createChemicalOrganization(Ecosystem system, OperationalRegion region,
            BusinessUnit chemEnterprise, int counter) {

        MasterConfiguration instance = MasterConfiguration.getInstance();
        try {
            instance.loadData();
        } catch (JsonMappingException ex) {
            Logger.getLogger(ConfigureASystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigureASystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> orgNames = instance.getOrganizationData().get("CHEMICAL");
        for (String name : orgNames) {

            String newName = name + "_" + region.getType().toString();

            Organization organization = system.getOrganizationDirectory()
                    .addOrganization(newName, chemEnterprise.getEnterpriseId(),
                            BusinessUnitType.CHEMICAL);

            region.getOrganizationList().add(organization);
            chemEnterprise.getOrganizationList().add(organization);
            organization.setEnterpriseId(chemEnterprise.getEnterpriseId());
            organization.setNetworkId(region.getRegionId());

            createOrganizationAdmin(system, region, chemEnterprise, organization, counter);
            counter++;

        };

    }

    public static void createOrganizationAdmin(Ecosystem system, OperationalRegion region,
            BusinessUnit enterprise, Organization organization, int counter) {

        BusinessUser organizationAdmin = (BusinessUser) system
                .getUserDirectory().addUser(UserType.BU,
                        enterprise.getUnitType(),
                        RoleType.ORG_ADMIN,
                        region.getType() + "_" + enterprise.getUnitType() + "_" + counter, "123");

        region.getUserList().add(organizationAdmin);
        enterprise.getUserList().add(organizationAdmin);
        organization.getUserList().add(organizationAdmin);

        organizationAdmin.setNetworkId(region.getRegionId());
        organizationAdmin.setEnterpriseId(enterprise.getEnterpriseId());
        organizationAdmin.setOrganizationId(organization.getOrgId());
        organizationAdmin.setFirstname(organizationAdmin.getUsername());
        organizationAdmin.setLastname("");

    }

    public static void createDrugOrganization(Ecosystem system, OperationalRegion region,
            BusinessUnit drugEnterprise, int counter) {

        MasterConfiguration instance = MasterConfiguration.getInstance();
        try {
            instance.loadData();
        } catch (JsonMappingException ex) {
            Logger.getLogger(ConfigureASystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigureASystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> orgNames = instance.getOrganizationData().get("DRUG");
        for (String name : orgNames) {

            String newName = name + "_" + region.getType().toString();

            Organization organization = system.getOrganizationDirectory()
                    .addOrganization(newName, drugEnterprise.getEnterpriseId(),
                            BusinessUnitType.DRUG);

            region.getOrganizationList().add(organization);
            drugEnterprise.getOrganizationList().add(organization);
            organization.setEnterpriseId(drugEnterprise.getEnterpriseId());
            organization.setNetworkId(region.getRegionId());

            createOrganizationAdmin(system, region, drugEnterprise, organization, counter);
            counter++;

        };
    }

    public static void createHospitalOrganization(Ecosystem system, OperationalRegion region,
            BusinessUnit hospitalEnterprise, int counter) {

        MasterConfiguration instance = MasterConfiguration.getInstance();
        try {
            instance.loadData();
        } catch (JsonMappingException ex) {
            Logger.getLogger(ConfigureASystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigureASystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> orgNames = instance.getOrganizationData().get("MEDICARE");
        for (String name : orgNames) {

            String newName = name + "_" + region.getType().toString();

            Organization organization = system.getOrganizationDirectory()
                    .addOrganization(newName, hospitalEnterprise.getEnterpriseId(),
                            BusinessUnitType.MEDICARE);

            region.getOrganizationList().add(organization);
            hospitalEnterprise.getOrganizationList().add(organization);
            organization.setEnterpriseId(hospitalEnterprise.getEnterpriseId());
            organization.setNetworkId(region.getRegionId());

            createOrganizationAdmin(system, region, hospitalEnterprise, organization, counter);
            counter++;

        }

    }
}
