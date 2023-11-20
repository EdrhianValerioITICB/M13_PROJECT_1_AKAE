package cat.iticbcn.demo.Exception;

public class CompanyAndOfferNotConnectedException extends RuntimeException {
	
	public CompanyAndOfferNotConnectedException(Long idC, Long idO) {
		
		super("La oferta con id " + idO +"  no pertence a esta compañia " + idC);
		
	}
	
	//holaaaa

}
