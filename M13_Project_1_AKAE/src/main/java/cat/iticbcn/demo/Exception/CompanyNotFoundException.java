package cat.iticbcn.demo.Exception;

public class CompanyNotFoundException extends RuntimeException {

	public CompanyNotFoundException(Long id) {
		super(" Could not find company " + id);
	}

	public CompanyNotFoundException(String name) {
		super(" Could not find company " + name);
	}
}
