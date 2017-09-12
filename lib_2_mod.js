function Modification(rname, version, author, desc, dname) {
	this.rname = rname;
	this.version = version;
	this.author = author;
	this.desc = desc;
	this.dname = dname;
	this.on_load = function() {
	}
	this.on_unload = function() {
	}
}

function register_mod(mod) {
	if (!(mod instanceof Modification)) {
		dlog.warning("Invalid mod specified: " + mod);
		return;
	}
	ntv_Submit(mod);
}

function unregister_mod(mod) {
	if (!(mod instanceof Modification)) {
		dlog.warning("Invalid mod specified: " + mod);
		return;
	}
	ntv_Kick(mod);
}

function ModBuilder() {
	this.rname = "";
	this.version = "";
	this.author = "";
	this.desc = "";
	this.dname = "";
	this.withRealName = function(rname) {
		this.rname = rname;
		return this;
	}
	this.withVersion = function(version) {
		this.version = version;
		return this;
	}
	this.withAuthor = function(author) {
		this.author = author;
		return this;
	}
	this.withDesc = function(desc) {
		this.desc = desc;
		return this;
	}
	this.withDisplayName = function(dname) {
		this.dname = dname;
		return this;
	}
	this.build = function() {
		return new Modification(this.rname, this.version, this.author,
				this.desc, this.dname);
	}
}