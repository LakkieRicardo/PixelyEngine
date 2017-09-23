function makeTestMod() {
	var mod = new Mod("tmod", "1.0", "Lakkie", "A test modification for testing purposes", "Test Mod");
	return mod;
}

function initTestMod() {
	var mod = makeTestMod();
	register_mod(mod);
}

initTestMod();