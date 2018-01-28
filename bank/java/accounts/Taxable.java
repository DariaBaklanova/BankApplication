package com.java.accounts;

public interface Taxable {
		
	    void calculateTax( double taxRate );
	    
	    double getTaxAmount( );
	    
	    String createTaxStatement( );
}
