package cat.iticbcn.demo.Exception;

public class CompanyAndOfferNotConnectedException extends RuntimeException {
	
	public CompanyAndOfferNotConnectedException(Long idC, Long idO) {
		
		super("The offer with the id  " + idO +"  doesn't belong to this company " + idC);
		
	}
	
	//holaaaa

}