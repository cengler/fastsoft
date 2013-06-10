package cae.resource;

public enum FSImageEnum {

	PROGRAM_ICON {
		@Override
		public String resource() {
			return "ff32.png";
		}
	},
	EDIT {
		@Override
		public String resource() {
			return "edit.png";
		}
	}, 
	SEARCH {
		@Override
		public String resource() {
			return "Search.png";
		}
	}, 
	COPY {
		@Override
		public String resource() {
			return "copy.png";
		}
	}, 
	DELETE {
		@Override
		public String resource() {
			return "delete.png";
		}
	},
	INFO {
		@Override
		public String resource() {
			return "Info.png";
		}
	},
	SETTINGS {
		@Override
		public String resource() {
			return "Settings.png";
		}
	},
	TOOLS {
		@Override
		public String resource() {
			return "Tools.png";
		}
	},
	KEY {
		@Override
		public String resource() {
			return "Key.png";
		}
	},
	FOLDER {
		@Override
		public String resource() {
			return "Folder.png";
		}
	};
	
	
	public abstract String resource();
}
