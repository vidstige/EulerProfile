package net.projecteuler.android;

public class Profile {
	private final String name;
	private final String country;
	private final String language;
	private final int solved;
	private final Level level;
	
	public Profile(String profileName, String country, String language, int solved, int level)
	{
		name = profileName;
		this.country = country;
		this.language = language;
		this.solved = solved;
		this.level = new Level(level);
	}
	
	public String getName() { return name; }
	
	public int getSolved() { return solved; }
	
	public Level getLevel() { return level; }
	
	public String getLanguage() { return language; }
	
	public String getCountry() { return country; }
}
