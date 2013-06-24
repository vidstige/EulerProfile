package net.projecteuler.android;

public class Level {

	private int level;

	public Level(int level) {
		this.level = level;
	}
	
	public int getDrawableResource()
	{
		switch (level)
		{
			case 0: return R.drawable.blank;
			case 1: return R.drawable.tetrahedron;
			case 2: return R.drawable.cube;
			case 3: return R.drawable.octahedron;
			case 4: return R.drawable.dodecahedron;
			case 5: return R.drawable.icosahedron;
			case 6: return R.drawable.sphere;
			case 7: return R.drawable.veterans;
			case 8: return R.drawable.eulerians;
		}
		// TODO: unknown
		return R.drawable.icon;
	}
	
	public String toString() { return Integer.toString(level); }
}
