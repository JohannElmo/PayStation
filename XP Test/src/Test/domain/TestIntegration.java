package Test.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import paystation.domain.LinearRateStrategy;
import paystation.domain.PayStation;
import paystation.domain.PayStationImpl;

class TestIntegration {
	PayStation ps;
	@BeforeEach
	void setUp() throws Exception {
		ps = new PayStationImpl(new LinearRateStrategy() );
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
