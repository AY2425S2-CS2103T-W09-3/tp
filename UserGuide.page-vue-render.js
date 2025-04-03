
    var pageVueRenderFn = function noop (a, b, c) {};
    var pageVueStaticRenderFns = [function anonymous(
) {
with(this){return _c('h1',{attrs:{"id":"medlogger-user-guide"}},[_v("MedLogger User Guide 🏥"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#medlogger-user-guide","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("MedLogger is a "),_c('strong',[_v("desktop app for managing contacts, optimized for use via a Command Line Interface (CLI)")]),_v(" while still benefiting from a Graphical User Interface (GUI). If you can type fast, MedLogger helps you complete contact management tasks "),_c('strong',[_v("faster")]),_v(" than traditional GUI apps.")])}
},function anonymous(
) {
with(this){return _c('h2',{attrs:{"id":"quick-start"}},[_v("Quick Start"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#quick-start","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Ensure you have "),_c('strong',[_v("Java 17")]),_v(" or above installed on your computer.")]),_v(" "),_c('li',[_c('strong',[_v("Mac users:")]),_v(" Follow the exact JDK installation steps mentioned "),_c('a',{attrs:{"href":"https://se-education.org/guides/tutorials/javaInstallationMac.html"}},[_v("here")]),_v(".")])])}
},function anonymous(
) {
with(this){return _c('ol',[_c('li',[_c('p',[_c('strong',[_v("Download")]),_v(" the latest "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v(".jar")]),_v(" file from "),_c('a',{attrs:{"href":"https://github.com/se-edu/addressbook-level3/releases"}},[_v("here")]),_v(".")])]),_v(" "),_c('li',[_c('p',[_c('strong',[_v("Move")]),_v(" the file to the folder you want as the "),_c('em',[_v("home folder")]),_v(" for MedLogger.")])]),_v(" "),_c('li',[_c('p',[_c('strong',[_v("Run MedLogger")]),_v(":")]),_v(" "),_c('ul',[_c('li',[_v("Open a "),_c('strong',[_v("command terminal")]),_v(".")]),_v(" "),_c('li',[_v("Navigate ("),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("cd")]),_v(") to the folder where the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v(".jar")]),_v(" file is located.")]),_v(" "),_c('li',[_v("Run the following command:"),_c('pre',[_c('code',{pre:true,attrs:{"class":"hljs sh"}},[_c('span',[_v("java -jar medlogger.jar\n")])])])]),_v(" "),_c('li',[_v("A GUI similar to the image below should appear in a few seconds:"),_c('br'),_v(" "),_c('a',{attrs:{"href":"/tp/images/Ui.png","target":"_self"}},[_c('img',{staticClass:"img-fluid",attrs:{"src":"/tp/images/Ui.png","alt":"UI"}})])]),_v(" "),_c('li',[_v("The app will load with "),_c('strong',[_v("sample data")]),_v(".")])])]),_v(" "),_c('li',[_c('p',[_v("Type the command in the command box and press Enter to execute it. e.g. typing "),_c('strong',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("help")])]),_v(" and pressing Enter will open the help window."),_c('br'),_v("\nSome example commands you can try:")]),_v(" "),_c('ul',[_c('li',[_c('p',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("list")]),_v(" : Lists all contacts.")])]),_v(" "),_c('li',[_c('p',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 d/2024-12-31 t/friends t/owesMoney")]),_v(" : Adds a contact named "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("John Doe")]),_v(" to the MedLogger.")])]),_v(" "),_c('li',[_c('p',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete 3")]),_v(" : Deletes the 3rd contact shown in the current list.")])]),_v(" "),_c('li',[_c('p',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("clear")]),_v(" : Deletes all contacts.")])]),_v(" "),_c('li',[_c('p',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("exit")]),_v(" : Exits the app.")])])])]),_v(" "),_c('li',[_c('p',[_v("Refer to the "),_c('a',{attrs:{"href":"#features"}},[_v("Features")]),_v(" below for details of each command.")])])])}
},function anonymous(
) {
with(this){return _c('h2',{attrs:{"id":"features"}},[_v("Features"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#features","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_c('strong',[_v("Notes about the command format:")]),_c('br')])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('p',[_v("Words in "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("UPPER_CASE")]),_v(" are the parameters to be supplied by the user."),_c('br'),_v("\ne.g. in "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/NAME")]),_v(", "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("NAME")]),_v(" is a parameter which can be used as "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/John Doe")]),_v(".")])]),_v(" "),_c('li',[_c('p',[_v("Items in square brackets are optional."),_c('br'),_v("\ne.g "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("n/NAME [t/TAG]")]),_v(" can be used as "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("n/John Doe t/friend")]),_v(" or as "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("n/John Doe")]),_v(".")])]),_v(" "),_c('li',[_c('p',[_v("Items with "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("…")]),_v("​ after them can be used multiple times including zero times."),_c('br'),_v("\ne.g. "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("[t/TAG]…​")]),_v(" can be used as "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}}),_v(" (i.e. 0 times), "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("t/friend")]),_v(", "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("t/friend t/family")]),_v(" etc.")])]),_v(" "),_c('li',[_c('p',[_v("Parameters can be in any order."),_c('br'),_v("\ne.g. if the command specifies "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("n/NAME p/PHONE_NUMBER")]),_v(", "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("p/PHONE_NUMBER n/NAME")]),_v(" is also acceptable.")])]),_v(" "),_c('li',[_c('p',[_v("Extraneous parameters for commands that do not take in parameters (such as "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("help")]),_v(", "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("list")]),_v(", "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("exit")]),_v(" and "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("clear")]),_v(") will be ignored."),_c('br'),_v("\ne.g. if the command specifies "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("help 123")]),_v(", it will be interpreted as "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("help")]),_v(".")])]),_v(" "),_c('li',[_c('p',[_v("If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.")])])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"viewing-help-help"}},[_v("Viewing help : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("help")]),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#viewing-help-help","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_c('a',{attrs:{"href":"/tp/images/helpMessage.png","target":"_self"}},[_c('img',{staticClass:"img-fluid",attrs:{"src":"/tp/images/helpMessage.png","alt":"help message"}})])])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("help")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"adding-a-person-add"}},[_v("Adding a person: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add")]),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#adding-a-person-add","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/DATE [t/TAG]…​")])])}
},function anonymous(
) {
with(this){return _c('p',[_c('strong',[_v("Tip:")]),_v(" A person can have any number of tags (including 0)")])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 1, #02-24 d/2024-12-01")])]),_v(" "),_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 d/2024-12-31 t/friends t/owesMoney")])])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"remark-a-person-remark"}},[_v("Remark a person : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remark")]),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#remark-a-person-remark","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remark INDEX r/REMARK")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Add a remark for the person at the specified "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("INDEX")]),_v(".")]),_v(" "),_c('li',[_v("The index refers to the index number shown in the displayed person list.")]),_v(" "),_c('li',[_v("The index "),_c('strong',[_v("must be a positive integer")]),_v(" 1, 2, 3, …​")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remark 1 r/important")]),_v(".")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"listing-all-persons-list"}},[_v("Listing all persons : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("list")]),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#listing-all-persons-list","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("list")]),_v(": Shows a list of all persons in the Med Logger.")]),_v(" "),_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("list l/LIMIT")]),_v(": Show a list of n "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("LIMIT")]),_v(" persons in the Med Loggers.")])])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("list")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"editing-a-person-edit"}},[_v("Editing a person : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("edit")]),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#editing-a-person-edit","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [d/DATE] [a/ADDRESS] [t/TAG]…​")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Edits the person at the specified "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("INDEX")]),_v(". The index refers to the index number shown in the displayed person list. The index "),_c('strong',[_v("must be a positive integer")]),_v(" 1, 2, 3, …​")]),_v(" "),_c('li',[_v("At least one of the optional fields must be provided.")]),_v(" "),_c('li',[_v("Existing values will be updated to the input values.")]),_v(" "),_c('li',[_v("When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.")]),_v(" "),_c('li',[_v("You can remove all the person’s tags by typing "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("t/")]),_v(" without\nspecifying any tags after it.")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("edit 1 p/91234567 e/johndoe@example.com")]),_v(" Edits the phone number and email address of the 1st person to be "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("91234567")]),_v(" and "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("johndoe@example.com")]),_v(" respectively.")]),_v(" "),_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("edit 2 n/Betsy Crower t/")]),_v(" Edits the name of the 2nd person to be "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Betsy Crower")]),_v(" and clears all existing tags.")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"locating-persons-by-name-find"}},[_v("Locating persons by name: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("find")]),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#locating-persons-by-name-find","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("find KEYWORD [MORE_KEYWORDS]")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("The search is case-insensitive. e.g "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("hans")]),_v(" will match "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Hans")])]),_v(" "),_c('li',[_v("The order of the keywords does not matter. e.g. "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Hans Bo")]),_v(" will match "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Bo Hans")])]),_v(" "),_c('li',[_v("Only the name is searched.")]),_v(" "),_c('li',[_v("Only full words will be matched e.g. "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Han")]),_v(" will not match "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Hans")])]),_v(" "),_c('li',[_v("Persons matching at least one keyword will be returned (i.e. "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("OR")]),_v(" search).\ne.g. "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Hans Bo")]),_v(" will return "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Hans Gruber")]),_v(", "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Bo Yang")])])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("find John")]),_v(" returns "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("john")]),_v(" and "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("John Doe")])]),_v(" "),_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("find alex david")]),_v(" returns "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Alex Yeoh")]),_v(", "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("David Li")]),_c('br'),_v(" "),_c('a',{attrs:{"href":"/tp/images/findAlexDavidResult.png","target":"_self"}},[_c('img',{staticClass:"img-fluid",attrs:{"src":"/tp/images/findAlexDavidResult.png","alt":"result for 'find alex david'"}})])])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"deleting-a-person-delete"}},[_v("Deleting a person : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete")]),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#deleting-a-person-delete","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete INDEX")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("Deletes the person at the specified "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("INDEX")]),_v(".")]),_v(" "),_c('li',[_v("The index refers to the index number shown in the displayed person list.")]),_v(" "),_c('li',[_v("The index "),_c('strong',[_v("must be a positive integer")]),_v(" 1, 2, 3, …​")])])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("list")]),_v(" followed by "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete 2")]),_v(" deletes the 2nd person in the Med Logger.")]),_v(" "),_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("find Betsy")]),_v(" followed by "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete 1")]),_v(" deletes the 1st person in the results of the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("find")]),_v(" command.")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"clearing-all-entries-clear"}},[_v("Clearing all entries : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("clear")]),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#clearing-all-entries-clear","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("clear")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"exiting-the-program-exit"}},[_v("Exiting the program : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("exit")]),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#exiting-the-program-exit","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Format: "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("exit")])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"exporting-the-data-export"}},[_v("Exporting the data : "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("export")]),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#exporting-the-data-export","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("export csv")])]),_v(" "),_c('li',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("export json")])])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"saving-the-data"}},[_v("Saving the data"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#saving-the-data","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"editing-the-data-file"}},[_v("Editing the data file"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#editing-the-data-file","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("MedLogger data are saved automatically as a JSON file "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("[JAR file location]/data/medlogger.json")]),_v(". Advanced users are welcome to update data directly by editing that data file.")])}
},function anonymous(
) {
with(this){return _c('p',[_c('strong',[_v("Caution:")]),_v("\nIf your changes to the data file makes its format invalid, MedLogger will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it."),_c('br'),_v("\nFurthermore, certain edits can cause the MedLogger to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.")])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"archiving-data-files-coming-in-v2-0"}},[_v("Archiving data files "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("[coming in v2.0]")]),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#archiving-data-files-coming-in-v2-0","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_c('em',[_v("Details coming soon ...")])])}
},function anonymous(
) {
with(this){return _c('h2',{attrs:{"id":"faq"}},[_v("FAQ"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#faq","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_c('strong',[_v("Q")]),_v(": How do I transfer my data to another Computer?"),_c('br'),_v(" "),_c('strong',[_v("A")]),_v(": Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MedLogger home folder.")])}
},function anonymous(
) {
with(this){return _c('h2',{attrs:{"id":"known-issues"}},[_v("Known issues"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#known-issues","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('ol',[_c('li',[_c('strong',[_v("When using multiple screens")]),_v(", if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("preferences.json")]),_v(" file created by the application before running the application again.")]),_v(" "),_c('li',[_c('strong',[_v("If you minimize the Help Window")]),_v(" and then run the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("help")]),_v(" command (or use the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Help")]),_v(" menu, or the keyboard shortcut "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("F1")]),_v(") again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.")])])}
},function anonymous(
) {
with(this){return _c('h2',{attrs:{"id":"command-summary"}},[_v("Command summary"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#command-summary","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('div',{staticClass:"table-responsive"},[_c('table',{staticClass:"markbind-table table table-bordered table-striped"},[_c('thead',[_c('tr',[_c('th',[_v("Action")]),_v(" "),_c('th',[_v("Format, Examples")])])]),_v(" "),_c('tbody',[_c('tr',[_c('td',[_c('strong',[_v("Add")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/DATE [t/TAG]…​")]),_v(" "),_c('br'),_v(" e.g., "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 d/2024-12-31 t/friend t/colleague")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Remark")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remark INDEX r/REMARK")]),_c('br'),_v(" e.g., "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remark 1 r/important")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Clear")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("clear")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Delete")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete INDEX")]),_c('br'),_v(" e.g., "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("delete 3")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Edit")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [d/DATE] [t/TAG]…​")]),_c('br'),_v(" e.g.,"),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("edit 2 n/James Lee e/jameslee@example.com")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Find")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("find KEYWORD [MORE_KEYWORDS]")]),_c('br'),_v(" e.g., "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("find James Jake")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("List")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("list")]),_v(" or "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("list l/LIMIT")])])]),_v(" "),_c('tr',[_c('td',[_c('strong',[_v("Help")])]),_v(" "),_c('td',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("help")])])])])])])}
},function anonymous(
) {
with(this){return _c('footer',[_c('div',{staticClass:"text-center"},[_c('small',[_v("["),_c('span',[_c('strong',[_v("Powered by")])]),_v(" "),_c('img',{attrs:{"src":"https://markbind.org/favicon.ico","width":"30"}}),_v(" "),_c('a',{attrs:{"href":"https://markbind.org/"}},[_v("MarkBind 5.6.0")]),_v(", generated on Thu, 3 Apr 2025, 21:33:50 GMT+8]")])])])}
}];
  