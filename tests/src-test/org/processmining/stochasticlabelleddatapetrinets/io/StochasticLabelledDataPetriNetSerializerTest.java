package org.processmining.stochasticlabelleddatapetrinets.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;
import org.processmining.stochasticlabelleddatapetrinet.StochasticLabelledDataPetriNet;
import org.processmining.stochasticlabelleddatapetrinet.StochasticLabelledDataPetriNetWeightsDataDependent;
import org.processmining.stochasticlabelleddatapetrinets.SimpleTestLog;

public class StochasticLabelledDataPetriNetSerializerTest {
	
	@Test
	public void testWriteSLDPN() throws IOException {
		StochasticLabelledDataPetriNetWeightsDataDependent net = SimpleTestLog.buildDataWeight2VariablesTestModel();
		
		Path tempFile = Files.createTempFile("sldpntests", ".sldpn");
		
		try {
			try (FileOutputStream fos = new FileOutputStream(tempFile.toFile())) {
				net.getDefaultSerializer().serialize(net, fos);	
			}	
		} finally {
			tempFile.toFile().delete();	
		}
		
	}
	
	@Test
	public void testWriteReadSLDPN() throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		StochasticLabelledDataPetriNetWeightsDataDependent net = SimpleTestLog.buildDataWeight2VariablesTestModel();
		
		Path tempFile = Files.createTempFile("sldpntests", ".sldpn");
	
		try {
		
			try (FileOutputStream fos = new FileOutputStream(tempFile.toFile())) {
				net.getDefaultSerializer().serialize(net, fos);
			}

			try (FileInputStream fos = new FileInputStream(tempFile.toFile())) {
				StochasticLabelledDataPetriNet sldpn = net.getDefaultSerializer().deserialize(fos);
				assertNotNull(sldpn);
				assertEquals(net.getNumberOfTransitions(), sldpn.getNumberOfTransitions());
				assertEquals(net.getNumberOfVariables(), sldpn.getNumberOfVariables());
				
			}

		} finally {
			tempFile.toFile().delete();	
		}
		
	}	
	

}
