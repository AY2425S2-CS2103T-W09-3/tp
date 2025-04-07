---
layout: default.md
title: "User Guide"
pageNav: 3
---

# MedLogger User Guide 🏥

MedLogger is a **desktop app for managing patient and visits, optimized for use via a Command Line Interface (CLI)** while still benefiting from a Graphical User Interface (GUI). If you can type fast, MedLogger helps you complete management tasks **faster** than traditional GUI apps.

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help : `help`](#viewing-help--help)
  - [Adding a patient: `person`](#adding-a-person-add)
  - [Remark a patient : `remark`](#remark-a-person--remark)
  - [Listing all patients : `list`](#listing-all-persons--list)
  - [Editing a patient : `editperson`](#editing-a-person--edit)
  - [Locating patients by name: `find`](#locating-persons-by-name-find)
  - [Deleting a patient : `delete`](#deleting-a-person--delete)
  - [Clearing all entries : `clear`](#clearing-all-entries--clear)
  - [Exiting the program : `exit`](#exiting-the-program--exit)
  - [Exporting the data : `export`](#exporting-the-data--export)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
  - [Archiving data files `[coming in v2.0]`](#archiving-data-files-coming-in-v20)
  - [Adding a visit: `visit`](#adding-a-visit-visit)
  - [Listing visits: `listvisits`](#listing-visits-listvisits)
  - [Sorting visits: `sortvisits`](#sorting-visits-sortvisits)
  - [Editing a visit: `editvisit`](#editing-a-visit-editvisit)
  - [Clearing visits: `clearvisits`](#clearing-visits-clearvisits)
- [FAQ](#faq)
- [Known issues](#known-issues)
- [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

- Ensure you have **Java 17** or above installed on your computer.  
- **Mac users:** Follow the exact JDK installation steps mentioned [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. **Download** the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).
2. **Move** the file to the folder you want as the _home folder_ for MedLogger.
3. **Run MedLogger**:
    - Open a **command terminal**.
    - Navigate (`cd`) to the folder where the `.jar` file is located.
    - Run the following command:
      ```sh
      java -jar medlogger.jar
      ```
    - A GUI similar to the image below should appear in a few seconds:  
      ![UI](images/Med%20logger%20UI.png)
    - The app will load with **sample data**.

4. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all patients.

   * `person n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 d/2024-12-31 t/friends t/owesMoney` : Adds a patient named `John Doe` to the MedLogger.

   * `delete 3` : Deletes the 3rd patient shown in the current list.

   * `clear` : Deletes all patients and visits.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless></box>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `person n/NAME`, `NAME` is a parameter which can be used as `person n/John Doe`.

* Items in square brackets are optional.<br>
  e.g in `person n/NAME [t/TAG]` can be used as `person n/John Doe t/friend` or as `person n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.<br>
  In particular, for items without `…`​ after them, the prefix for each item must appear at most once. <br>
  e.g. `person n/John Doe` is legal, but `person n/John Doe n/Alice` is not.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>
---
### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

---

### Adding a patient: `person`

Adds a patient to the Med Logger.

Format: `person n/NAME i/NRIC p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<box type="tip" seamless></box>

**Tip:** 
* A patient can have any number of tags (including 0)
* The NRIC, phone number, email must adhere to respective format constraint
</box>

Examples:
* `person n/John Doe i/A1234567S p/98765432 e/johnd@example.com a/311, Clementi Ave 1, #02-24 d/2024-12-01`
* `person n/John Doe i/A1234567S p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 d/2024-12-31 t/friends t/owesMoney`
![person command](images/person%20command%20example.png)

---

### Adding a remark to a patient : `remark`

Add a remark for a person from MedLogger.

Format: `remark INDEX r/REMARK`

* Add a remark for the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `remark 1 r/important`.
![remark command](images/remark%20command%20example.png)

---

### Listing all patients : `list`

* `list`: Shows a list of all patients in the Med Logger.
* `list l/LIMIT`: Show a list of n `LIMIT` patients in the Med Loggers. 

Format: `list [l/LIMIT]`
![list command](images/list%20command%20example.png)

---

### Editing a patient : `editperson`

Edits an existing patient in the Med Logger.

Format: `editperson INDEX [n/NAME] [i/NRIC] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the patient at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the patient will be removed i.e adding of tags is not cumulative.
* You can remove all the patient’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `editperson 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st patient to be `91234567` and `johndoe@example.com` respectively.
*  `editperson 2 n/Betsy Crower t/` Edits the name of the 2nd patient to be `Betsy Crower` and clears all existing tags.
![editperson command](images/editperson%20command%20example.png)

---

### Locating persons by name: `find`

Finds patients whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Patients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)
---
### Deleting a patient : `delete`

Deletes the specified patient from the Med Logger.

Format: `delete INDEX`

* Deletes the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd patient in the Med Logger.
* `find Betsy` followed by `delete 1` deletes the 1st patient in the results of the `find` command.
  ![before delete](images/delete%20command%20example%20before.png)
  ![after delete](images/delete%20command%20example%20after.png)
---
### Adding a visit: `visit`

Format: `visit i/NRIC [d/DATETIME] [r/REMARK] [diag/DIAGNOSIS] [med/MEDICATION] [f/FOLLOWUP]`
<box type="tip" seamless></box>

**Tip:**
* The NRIC and DATETIME must adhere to respective format constraint
* The NRIC must be present in Med logger(i.e. one and only one patient have the input NRIC). The visit will be automatically associated to that person with the same NRIC.
* if no DATETIME is provided, current time(i.e. time of command input will be used)
  </box>

Examples:
* `visit i/S1234567A r/Allergy`
* `visit i/S1234567A d/2024-12-31 11:11 diag/Flu symptoms`
  ![visit command](images/visit%20command%20example.png)

---

### Removing a visit: `deletevisit`
Format: `deletevisit INDEX`
* Deletes the visit at the specified `INDEX`.
* The index refers to the index number shown in the displayed visit list.
* The index **must be a positive integer** 1, 2, 3, …​

---

### Editing a visit: `editvisit`

Edits a visit in current visit record.

Format: `editvisit INDEX [i/NRIC] [d/DATETIME] [r/REMARK] [diag/DIAGNOSIS] [med/MEDICATION] [f/FOLLOWUP]`

- Edits the visit at the specified `INDEX`.
- The index refers to the index number shown in the displayed visit list.
- If NRIC is edited, the edited NRIC must belong to a patient already exist in the system.
- At least one optional field must be present.

Examples:
* `editvisit 1 d/2025-01-01 09:00`
* `editvisit 2 r/Updated diagnosis`
* `editvisit 3 i/S1234567A d/2024-12-01 10:00 r/Fever`
---

### Listing visits: `listvisits`

Lists visits stored in the system.

Format: 
* `listvisits` — lists all visits
* `listvisits i/NRIC` — lists visits of a specific patient
* `listvisits l/LIMIT` — lists up to LIMIT visits
* `listvisits i/NRIC l/LIMIT` — lists up to LIMIT visits of a specific patient

Examples:
* `listvisits`
* `listvisits i/S1234567A`
* `listvisits l/3`
* `listvisits i/S1234567A l/5`

---

### Sorting visits: `sortvisits`

Sorts the currently displayed visits by visit time.

Format:
* `sortvisits` — sorts in ascending order
* `sortvisits desc` — sorts in descending order

Examples:
* `sortvisits`
* `sortvisits desc`

---

### Clearing all entries : `clear`

Clears all entries from the Med Logger.

Format: `clear`

---
### Clearing visits: `clearvisits`

Clears the visit panel.

Format: `clearvisits`

Example:
* `clearvisits` — removes all visits from the panel display (does not delete visit records)

---
### Exiting the program : `exit`

Exits the program.

Format: `exit`
---
### Exporting the data : `export`

Export the visits and persons data into either csv or json format. A save dialog will be prompted.

Format:
* `export csv`
* `export json`
---
### Saving the data

MedLogger data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
---
### Editing the data file

MedLogger data are saved automatically as a JSON file `[JAR file location]/data/medlogger.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless></box>

**Caution:**
If your changes to the data file makes its format invalid, MedLogger will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the MedLogger to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MedLogger home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add patient**    | `person n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS d/DATE [t/TAG]…​` <br> e.g., `person n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 d/2024-12-31 t/friend t/colleague`
**Remark**   | `remark INDEX r/REMARK`<br> e.g., `remark 1 r/important`
**Clear**  | `clear`
**Delete patient** | `delete INDEX`<br> e.g., `delete 3`
**Edit patient**   | `editperson INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [d/DATE] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find patient**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List patients**   | `list` or `list l/LIMIT`
**Add visit**   | `visit n/NAME i/NRIC [d/DATE_TIME] r/REMARK`
**List visits**    | `listvisits` or `listvisits l/LIMIT`
**Sort visits**    | `sortvisits` or `sortvisits desc`
**Edit visit**     | `editvisit INDEX [i/NRIC] [d/DATE_TIME] [r/REMARK]`
**Clear visits**   | `clearvisits`
**Export**      | `export csv` or `export json`
**Help**   | `help`
**Exit**   | `exit`
