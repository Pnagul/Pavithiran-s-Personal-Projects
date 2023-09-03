import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
//Name: Pavithiran Naguleswaran
//ID: 501162696

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			
			//try
			try{
				String action = scanner.nextLine();
					if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
				
				else if (action.equalsIgnoreCase("STORE"))	// List all songs
				{
					store.listAll(); 
				}
				else if (action.equalsIgnoreCase("SONGS"))	// List all songs
				{
					mylibrary.listAllSongs(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
				{
					mylibrary.listAllAudioBooks(); 
				}
				else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
				{
					mylibrary.listAllPodcasts(); 
				}
				else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
				{
					mylibrary.listAllArtists(); 
				}
				else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
				{
					mylibrary.listAllPlaylists(); 
				}
				// Download audiocontent (song/audiobook/podcast) from the store 
				// Specify the index of the content
				else if (action.equalsIgnoreCase("DOWNLOAD")) 
				{
					//variables to store user inputs
					int fromIndex = 0;
					int toIndex = 0;
					
					//user input for index for beginning of range
					System.out.print("From Content #: ");
					if (scanner.hasNextInt())
					{
						fromIndex = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					//user input for end of range
					System.out.print("To Content #: ");
					if (scanner.hasNextInt())
					{
						toIndex = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					//invalid index
					if(fromIndex < 1 || toIndex < fromIndex){
						System.out.println("Invalid Index!!");
					}
					else{
						for(int i = fromIndex; i<= toIndex; i++){
							AudioContent content = store.getContent(i);
							if (content == null)
								System.out.println("Content Not Found in Store");
							mylibrary.download(content);
							}
						
					}
					
										
				}
				// Get the *library* index (index of a song based on the songs list)
				// of a song from the keyboard and play the song 
				else if (action.equalsIgnoreCase("PLAYSONG")) 
				{
					int indexS = 0;

					System.out.print("Song Number: ");

					//Check to see if user input was an integer
					if (scanner.hasNextInt()){

						//set indexS to user input index
						indexS = scanner.nextInt();
						scanner.nextLine();

						mylibrary.playSong(indexS);
						
					}
					// Print error message if the song doesn't exist in the library
				}
				// Print the table of contents (TOC) of an audiobook that
				// has been downloaded to the library. Get the desired book index
				// from the keyboard - the index is based on the list of books in the library
				else if (action.equalsIgnoreCase("BOOKTOC")) 
				{
					int indexT = 0;

					//get user input
					System.out.print("Audio Book Number: ");
					//check to see if user input was an int
					if (scanner.hasNextInt()){
						//assign user input to variable
						indexT = scanner.nextInt();
						scanner.nextLine();

						mylibrary.printAudioBookTOC(indexT);
					}
				// Print error message if the book doesn't exist in the library
				}
				// Similar to playsong above except for audio book
				// In addition to the book index, read the chapter 
				// number from the keyboard - see class Library
				else if (action.equalsIgnoreCase("PLAYBOOK")) 
				{
					int indexB = 0;
					int indexC = 0;

					//User inut for book number
					System.out.print("Audio Book Number: ");
					
					//check if user input was an int
					if (scanner.hasNextInt()){
						//assign user input to variable 
						indexB = scanner.nextInt();
						scanner.nextLine();

						//User input for chpater number
						System.out.print("Chapter: ");
						//check to see if user input was an int
						if (scanner.hasNextInt()){
							//assign user input to variable
							indexC = scanner.nextInt();
							scanner.nextLine();
							
							
							mylibrary.playAudioBook(indexB, indexC);
							
						}
					}

					
				}
				// Print the episode titles for the given season of the given podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PODTOC")) 
				{
					
				}
				// Similar to playsong above except for podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number and the episode number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPOD")) 
				{
					
				}
				// Specify a playlist title (string) 
				// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYALLPL")) 
				{
					String title = "";

					//user input for title
					System.out.print("Playlist Title: ");
					//assign user input to variable
					title = scanner.next();
					scanner.nextLine();

					
					mylibrary.playPlaylist(title);

					
				}
				// Specify a playlist title (string) 
				// Read the index of a song/audiobook/podcast in the playist from the keyboard 
				// Play all the audio content 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) 
				{
					String titleP = "";
					int index = 0;

					//get user input for title
					System.out.print("Playlist Title: ");
					//store user input into variable
					titleP = scanner.next();
					scanner.nextLine();

					//get user input for index
					System.out.print("Content Number: ");
					//check to see if user input was an int
					if(scanner.hasNextInt()){
						//assign user input to variable
						index = scanner.nextInt();
						scanner.nextLine();

						
						mylibrary.playPlaylist(titleP, index);
					}
					


					
				}
				// Delete a song from the list of songs in mylibrary and any play lists it belongs to
				// Read a song index from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELSONG")) 
				{
					int indexD = 0;

					//user input for song number
					System.out.print("Library Song #: ");

					//if user input was an int
					if (scanner.hasNextInt()){
						//set index to user input
						indexD = scanner.nextInt();
						scanner.nextLine();

						
						mylibrary.deleteSong(indexD);
					}
						
				}
				// Read a title string from the keyboard and make a playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("MAKEPL")) 
				{
					String titleM = "";

					//get user input for title
					System.out.print("Playlist Title: ");
					//assign user input to variable
					titleM = scanner.next();
					scanner.nextLine();

					mylibrary.makePlaylist(titleM);
					
				}
				// Print the content information (songs, audiobooks, podcasts) in the playlist
				// Read a playlist title string from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
				{
					String titleP = "";
					//get user input for title
					System.out.print("Playlist Title: ");
					//assign user input to variable
					titleP = scanner.next();
					scanner.nextLine();

					mylibrary.printPlaylist(titleP);
					
				}
				// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
				// Read the playlist title, the type of content ("song" "audiobook" "podcast")
				// and the index of the content (based on song list, audiobook list etc) from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL")) 
				{
					int indexA = 0;
					String playTitle = "", typeC = "";

					//get user input for title
					System.out.print("Playlist Title: ");
					//store user input into variable
					playTitle = scanner.next();
					scanner.nextLine();

					//get user input for type
					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
					//store user input to variable
					typeC = scanner.next();
					scanner.nextLine();

					//get user input for content number
					System.out.print("Library Content #: ");
					//if user input was an int
					if(scanner.hasNextInt()){
						//assign user input to variable
						indexA = scanner.nextInt();
						scanner.nextLine();

						mylibrary.addContentToPlaylist(typeC, indexA, playTitle);
					}
					
				}
				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELFROMPL")) 
				{
					String titleDel ="";
					int indexDel = 0;

					//get user input for title
					System.out.print("Playlist Title: ");
					//assign user input to variable
					titleDel = scanner.next();
					scanner.nextLine();

					//get user input for content number
					System.out.print("Playlist Content #: ");

					//check to see if user input was an int
					if (scanner.hasNextInt()){
						//assign user input to variable
						indexDel = scanner.nextInt();
						scanner.nextLine();

						mylibrary.delContentFromPlaylist(indexDel, titleDel);
					}
					
				}
				
				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					mylibrary.sortSongsByYear();
				}
				else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					mylibrary.sortSongsByName();
				}
				else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					mylibrary.sortSongsByLength();
				}

				//searchA
				else if (action.equalsIgnoreCase("searcha")){
					//variable to store user input
					String name = "";

					//user input for name
					System.out.print("Artist/Author: ");
					if(scanner.hasNext()){
						name = scanner.nextLine();
					}
					//call staore function
					store.searchA(name);

				}

				//search
				else if(action.equalsIgnoreCase("search")){
					//variable to store user input
					String title = "";

					//Get user input for title
					System.out.print("Title: ");
					if(scanner.hasNext()){
						title = scanner.nextLine();
					}
					//call store function
					store.search(title);
				}

				//SearchG
				else if(action.equalsIgnoreCase("searchg")){
					//variable to store user input
					String genre = "";

					//User input for genre
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
					if(scanner.hasNext()){
						genre = scanner.nextLine();
					}
					//call store function
					store.searchG(genre);
				}

				//DownloadA
				else if(action.equalsIgnoreCase("DOWNLOADA")){
					//variable to store user input
					String name = "";
					
					//user input for name
					System.out.print("Artist/Author name: ");
					if(scanner.hasNext()){
						name = scanner.nextLine();
					}
					//call function and store into new arraylist 
					ArrayList<Integer> artistList = store.getArtist(name);
					//If arraylist is null
					if(artistList == null){
						System.out.println("Invalid artist");
					}
					//else not null
					else{
						//for loop to arrayList
						for(int i : artistList){
							AudioContent content = store.getContent(i);
							if(content == null){
								System.out.println("Content not found in store");
							}
							mylibrary.download(content);
						}
					}
					
				}

				//DownloadG
				else if(action.equalsIgnoreCase("DOWNLOADG")){
					//variable to store user input
					String genre = "";
					
					//Get user input for genre
					System.out.print("Genre: ");
					if(scanner.hasNext()){
						genre = scanner.nextLine();
					}
					//call function and store into new arraylist 
					ArrayList<Integer> genreList = store.getGenre(genre);
					//new arraylist is null
					if(genreList == null){
						System.out.println("Invalid Genre");
					}
					else{
						for(int i : genreList){
							AudioContent content = store.getContent(i);
							if(content == null){
								System.out.println("Content not found in store");
							}
							mylibrary.download(content);
						}
					}
					
				}
				
			}
			//catch audiobook alreay exists exception
			catch(AudioBookAlreadyExistsException e){
				System.out.println(e.getMessage());
			}

			//catch audiobook not found exception
			catch(AudioBookNotFoundException e){
				System.out.println(e.getMessage());
			}

			//catch song already exists exception
			catch(SongAlreadyExistsException e){
				System.out.println(e.getMessage());
			}

			//catch playlist already exists exception
			catch(PlaylistAlreadyExistsException e){
				System.out.println(e.getMessage());
			}

			//catch playlist not found exception
			catch(PlaylistNotFoundException e){
				System.out.println(e.getMessage());
			}

			//catch content not found exception
			catch(ContentNotFoundException e){
				System.out.println(e.getMessage());
			}

			//catch type not found exception
			catch(TypeNotFoundException e){
				System.out.println(e.getMessage());
			}

			System.out.print("\n>");
		}
	}
}
