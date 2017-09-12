function makeTestMod() {
	var modBuilder = new ModBuilder();
	// ModBuilder helps when reading over old code.
	modBuilder = modBuilder.withRealName("test").withVersion("1.0").withAuthor(
			"Lakkie").withDesc("Test Mod").withDisplayName("Test Mod");
	var mod = modBuilder.build();
	return mod;
}

function initTestMod() {
	var mod = makeTestMod();
	register_mod(mod);
}

initTestMod();