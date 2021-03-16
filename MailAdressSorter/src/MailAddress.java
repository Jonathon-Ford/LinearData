//Class that implements the interface
	public class MailAddress implements MailAddressInterface
	{
		
		//Declare Variables for setters
		protected String name;
		protected String addressLine1;
		protected String addressLine2;
		protected String city;
		protected String state;
		protected int zip;
		
		//Constructor
		public MailAddress(String name, String addressLine1, String addressLine2, String city, String state, int zip) {
			this.name = name;
			this.addressLine1 = addressLine1;
			this.addressLine2 = addressLine2;
			this.city = city;
			this.state = state;
			this.zip = zip;
		}
		
		//Setters (unnecessary for this project but makes the class less rigid)
		public void setName(String newName) {
			this.name = newName;
		}
		
		public void setAddressLine1(String newAddressLine1) {
			this.addressLine1 = newAddressLine1;
		}

		public void setAddressLine2(String newAddressLine2) {
			this.addressLine2 = newAddressLine2;
		}

		public void setCity(String newCity) {
			this.city = newCity;
		}

		
		public void setState(String newState) {
			this.state = newState;
		}

		public void setZipCode(int newZip) {
			this.zip = newZip;
		}

		//A test to give all of the saved info back
		public String test()
		{
			return name +", "+ addressLine1 +", "+ addressLine2 +", "+ city +", "+ state +", "+ zip;
		}
		
		
		//Getters
		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getAddressLine1() {
			return addressLine1;
		}

		@Override
		public String getAddressLine2() {
			return addressLine2;
		}

		@Override
		public String getCity() {
			return city;
		}

		@Override
		public String getState() {
			return state;
		}

		@Override
		public int getZipCode() {
			return zip;
		}

		//Returns nth digit by doing {(zip % 10^n) - (zip % 10^n-1)} / 10^n-1
		@Override
		public int getZipCodeDigit(int digit) {
			return (int) (((zip % (Math.pow(10, digit))) - (zip % Math.pow(10, digit - 1))) / (Math.pow(10, digit - 1)) );
		}
		
	}