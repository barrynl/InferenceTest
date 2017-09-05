package nl.tno.openllet;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.vocabulary.RDFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import openllet.jena.PelletReasonerFactory;

public class AnotherTest {
	private static final Logger LOG = LoggerFactory.getLogger(AnotherTest.class);

	public static void main(String[] args) {
		String NS = "urn:x-hp-jena:eg/";

		// Build a trivial example data set
		Model rdfsExample = ModelFactory.createDefaultModel();

		Property p = rdfsExample.createProperty(NS, "p");
		Property q = rdfsExample.createProperty(NS, "q");
		rdfsExample.add(p, RDFS.subPropertyOf, q);
		rdfsExample.createResource(NS + "a").addProperty(p, "foo");

		// Reasoner reasoner = PelletReasonerFactory.theInstance().create(null);
		// InfModel inf = ModelFactory.createInfModel(reasoner, rdfsExample);
		InfModel inf = ModelFactory.createRDFSModel(rdfsExample);

		LOG.info("----------------before removal individual--------------------");
		inf.write(System.out, "TURTLE");

		rdfsExample.createResource(NS + "a").removeAll(p);

		inf.rebind();
		LOG.info("-----------------after removing individual-------------------");
		inf.write(System.out, "TURTLE");

	}

}
