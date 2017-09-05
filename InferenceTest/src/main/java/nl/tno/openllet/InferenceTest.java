package nl.tno.openllet;

import java.io.InputStream;
import java.util.List;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import openllet.jena.PelletReasonerFactory;

public class InferenceTest {

	private static final Logger LOG = LoggerFactory.getLogger(InferenceTest.class);

	private static final String fname = "/ontology-plus-data.ttl";

	public static void main(String[] args) {

		Model model = ModelFactory.createDefaultModel();
		InputStream is = InferenceTest.class.getResourceAsStream(fname);
		assert (is != null);

		assert (model.size() == 0);

		model.read(is, null, "TURTLE");

		Reasoner reasoner = PelletReasonerFactory.theInstance().create(null);
		InfModel inf = ModelFactory.createInfModel(reasoner, model);

//		printAllStatements(inf);

		printIndividualStatements(inf);

		addIndividualStatementToModel(inf);
		
//		printAllStatements(inf);

		printIndividualStatements(inf);

		removeIndividualStatementToModel(inf);

		inf.rebind();
		inf.reset();
		
		printIndividualStatements(inf);

//		printAllStatements(inf);
	}

	private static void printIndividualStatements(Model m) {
		LOG.info("---- print Individual Statements ----");
		Resource a = m.getResource("http://ontology.tno.nl/2017/7/untitled-ontology-103/indiv");

		StmtIterator si = a.listProperties();
		while (si.hasNext()) {
			Statement aStmt = si.next();
			LOG.info("Stmt: {}", aStmt);
		}
	}

	private static void addIndividualStatementToModel(Model m) {
		LOG.info("---- add Individual Statement ----");
		Resource s = m.createResource("http://ontology.tno.nl/2017/7/untitled-ontology-103/indiv");
		Property p = m.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
		Resource o = m.createResource("http://ontology.tno.nl/2017/7/untitled-ontology-103/TestClass");

		Statement stmt = ResourceFactory.createStatement(s, p, o);

		m.add(stmt);
	}

	private static void removeIndividualStatementToModel(Model m) {
		LOG.info("---- remove Individual Statements ----");
		Resource s = m.createResource("http://ontology.tno.nl/2017/7/untitled-ontology-103/indiv");
		Property p = m.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
		Resource o = m.createResource("http://ontology.tno.nl/2017/7/untitled-ontology-103/TestClass");

		Statement stmt = ResourceFactory.createStatement(s, p, o);

		m.remove(stmt);
	}

	private static void printAllStatements(Model m) {
		LOG.info("---- print all statements ---");
		StmtIterator iter = m.listStatements();
		while (iter.hasNext()) {
			Statement s = iter.next();

			LOG.info("Stmt: {}", s);
		}
	}

}
