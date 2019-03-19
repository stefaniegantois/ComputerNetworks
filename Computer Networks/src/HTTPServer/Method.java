package HTTPServer;

public enum Method {
	GET{
		public String getMethod() {
			return "GET";
		}
		
	},
	HEAD{
		public String getMethod() {
			return "HEAD";
		}
	},
	PUT{
		public String getMethod() {
			return "PUT";
		}
	},
	POST{
		public String getMethod() {
			return "POST";
		}
	},
	ERROR{
		public String getMethod() {
			return "ERROR";
		}
	};
	
	public abstract String getMethod();
}
