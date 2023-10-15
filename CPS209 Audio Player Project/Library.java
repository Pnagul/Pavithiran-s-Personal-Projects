import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
Name: Pavi
ID: 501162696
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
    //private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs = new ArrayList<Song>(); 
		audiobooks = new ArrayList<AudioBook>(); 
		playlists  = new ArrayList<Playlist>();
	    //podcasts = new ArrayList<Podcast>(); 
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public void download(AudioContent content)
	{
		//switch case to look at the type of audio content
		switch(content.getType()){
			//type is song
			case "SONG":
			//Make audiocontent onject into song object
				Song tempS = (Song) content;
				//Only add object into arraylist if it is not already there
				if (!songs.contains(tempS)){
					songs.add(tempS);
					System.out.println("Song " + content.getTitle() + " Added to Library!");
				}
				//if already there, assign error message
				else{
					//throw new exception
					SongAlreadyExistsException e = new SongAlreadyExistsException("Song " + content.getTitle() + " already in library!!");
					System.out.println(e.getMessage());
				}
				break;
			//type is audiobook
			case "AUDIOBOOK":
				//make audiocontent object into audiobook object
				AudioBook tempA = (AudioBook) content;
				//add object to arraylist if it is not already there
				if (!audiobooks.contains(tempA)){
					audiobooks.add(tempA);
					System.out.println("AudioBook " + content.getTitle() + " Added to Library!");
				}
				//otherwise assign error message
				else{
					//throw new exception
					AudioBookAlreadyExistsException e = new AudioBookAlreadyExistsException("AudioBook " + content.getTitle() + " already in library!!");
					System.out.println(e.getMessage());

				}
				break;
		}
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		//for loop to go through songs arraylist
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		//for loop to go through audiobooks arraylist
		for (int i = 0; i < audiobooks.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();	
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		/* 
		for (int i = 0; i < podcasts.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			podcasts.get(i).printInfo();
			System.out.println();	
		}
		*/
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		//for loop to go through plylists arraylist
		for (int i = 0; i < playlists.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			System.out.print(playlists.get(i).getTitle());
			System.out.println();	
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names
		ArrayList<String> artist = new ArrayList<String>();
		//for loop to go through songs arraylist
		for (int i = 0; i<songs.size(); i++){
			//add to artist arraylist if it is not already there
			if (!artist.contains(songs.get(i).getArtist())){
				artist.add(songs.get(i).getArtist());
			}
		}
		//for loop to go through artist arralylist and print them out
		for (int x = 0; x<artist.size(); x++){
			int index = x+1;
			System.out.print("" + index + ". ");
			System.out.print(artist.get(x));
			System.out.println();
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index)
	{
		//index is out of bounds
		if (index < 1 || index > songs.size()){
			//throw new exception
			throw new SongNotFoundException("Song not found!");
		}
		
		
		//for loop to go through playlists arraylist
		for (Playlist temp : playlists){
			if (temp.getContent().indexOf(songs.get(index-1)) != -1){
				//delete song from playlist
				temp.deleteContent((temp.getContent().indexOf(songs.get(index-1)))+1);
			}
		}
		//remove song from song playlist
		songs.remove(index-1);
		
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		Collections.sort(songs, new SongYearComparator());
	
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b){
			return a.getYear() - b.getYear();
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 	// Use Collections.sort() 
	 	Collections.sort(songs, new SongLengthComparator());
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b){
			return a.getLength() - b.getLength();
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);


	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index)
	{
		//index is out of bounds
		if (index < 1 || index > songs.size())
		{
			//throw new exception
			throw new SongNotFoundException("Song Not Found");
		}
		//play the specific song from the songs list
		songs.get(index-1).play();
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		return false;
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		return false;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter)
	{
		//index out of bounds
		if (index < 1 || index > audiobooks.size())
		{
			//throw new exception
			throw new AudioBookNotFoundException("Audiobook Not Found");
		}
		//get chapter of audiobook and play
		audiobooks.get(index-1).selectChapter(chapter);
		audiobooks.get(index-1).play();
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index)
	{
		//index out of bounds
		if (index < 1 || index > audiobooks.size()){
			//throw new exception
			throw new AudioBookNotFoundException("Audiobook not found!!");
		}
		//print chapter titles of audiobook
		audiobooks.get(index-1).printTOC();
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)
	{
		//make title into a playlist object
		Playlist temp = new Playlist(title);
		//create boolean check as method to check if playlist already exits
		boolean check = false;

		//for loop to go through playlists list
		for (int i = 0; i<playlists.size(); i++){
			//if playlist with the same name exits
			if (playlists.get(i).equals(temp)){
				//check is true
				check = true;
				//throw new exception
				throw new PlaylistAlreadyExistsException("Playlist already exists!!");  
			}
		}
		//if check is false
		//playlist with same name is not found
		if (!(check)){
			//add playlist to list
			playlists.add(temp);
		}
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)
	{
		//make title into playlist object for searching purposes
		Playlist temp = new Playlist(title);
		//for loop to go through playlists list
		for (int i = 0; i<playlists.size(); i++){
			//if only playlist exits with the same name
			//print contents
			if (playlists.get(i).equals(temp)){
				playlists.get(i).printContents();
				//use return to exit method and not run code below
				return;
				
			}
		}
		//throw new exception
		throw new PlaylistNotFoundException("Playlist does not exist");
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle)
	{
		//make playlist title into playlist object for searching purposes
		Playlist temp = new Playlist(playlistTitle);
		//for loop to go through playlists list
		for (int i = 0; i<playlists.size(); i++){
			//if the playlist exists with the same name
			if (playlists.get(i).equals(temp)){
				//play all content in playlist
				playlists.get(i).playAll();
				//use return to exit method and not run code below
				return;
			}
		}
		//throw new exception
		throw new PlaylistNotFoundException("Playlist does not exist");
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL)
	{
		//make playlist title into playlist object for searching purposes
		Playlist temp = new Playlist(playlistTitle);
		//for loop to go through playlists list
		for (int i = 0; i<playlists.size(); i++){
			//if playlist exists with same name
			if (playlists.get(i).equals(temp)){
				//eror check index to see if its a valid index
				if (indexInPL < 1 || indexInPL > playlists.get(i).getContent().size()){
					//throw new exception
					throw new ContentNotFoundException("Content not found!!");
				}
				//play specific content in playlist
				playlists.get(i).play(indexInPL);
				//use return to exit method and not run code below
				return;
			}
		}
		//throw new exception
		throw new PlaylistNotFoundException("Playlist not found");
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{
		//make playlist title playlist object for searching purposes
		Playlist temp = new Playlist(playlistTitle);
		//for loop to search through playlists list
		for (int i = 0; i<playlists.size(); i++){
			//if playlist with mathcing name exists
			if (playlists.get(i).equals(temp)){
				//type was song
				if (type.equalsIgnoreCase("Song")){

					//eror check index to see if its a valid index
					if (index < 1 || index > songs.size()){
						//throw new exception
						throw new SongNotFoundException("Song not found!!");
					}
					playlists.get(i).addContent(songs.get(index-1));
					//use return to exit method and not run code below
					return;
				}
				//type was audiobook
				else if (type.equalsIgnoreCase("Audiobook")){

					//eror check index to see if its a valid index
					if (index < 1 || index > audiobooks.size()){
						//throw new exception
						throw new AudioBookNotFoundException("Audiobook not found!!");
					}
					playlists.get(i).addContent(audiobooks.get(index-1));
					//use return to exit method and not run code below
					return;
				}
				//inavlid type
				throw new TypeNotFoundException("Type not found");

			}
		}
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title)
	{
		//make title a playlist object for searching purposes
		Playlist temp = new Playlist(title);
		//search through playlist list with for loop
		for (int i = 0; i<playlists.size(); i++){
			//if playlist exists with same name
			if (playlists.get(i).equals(temp)){
				if (index < 1 || index > playlists.get(i).getContent().size()){
					//throw new exception
					throw new ContentNotFoundException("Content not found!!");
				}
				playlists.get(i).deleteContent(index);
				//use return to exit method and not run code below
				return;
			}
		}
		//error message
		throw new PlaylistNotFoundException("Playlist not found");
		
	}
	
}

//Exception class for audiobook already exists
class AudioBookAlreadyExistsException extends RuntimeException {
	public AudioBookAlreadyExistsException(String message){
		super(message);
	}
}

//Excpetion class for song already exists
class SongAlreadyExistsException extends RuntimeException {
	public SongAlreadyExistsException(String message){
		super(message);
	}
}

//Excoetion class for song not found
class SongNotFoundException extends RuntimeException {
	public SongNotFoundException(String message){
		super(message);
	}
}

//Excpetion class for audiobook not found
class AudioBookNotFoundException extends RuntimeException {
	public AudioBookNotFoundException(String message){
		super(message);
	}
}

//Excpetion class for playlist already exist
class PlaylistAlreadyExistsException extends RuntimeException {
	public PlaylistAlreadyExistsException(String message){
		super(message);
	}
}

//Excpetion class for playlist not found
class PlaylistNotFoundException extends RuntimeException {
	public PlaylistNotFoundException(String message){
		super(message);
	}
}

//Excpetion class for content not found
class ContentNotFoundException extends RuntimeException {
	public ContentNotFoundException(String message){
		super(message);
	}
}

//Excpetion class for type not found
class TypeNotFoundException extends RuntimeException {
	public TypeNotFoundException(String message){
		super(message);
	}
}