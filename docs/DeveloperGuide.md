---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# MedLogger Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts such as `CommandBox`, `ResultDisplay`, `PersonListPanel`, `VisitListPanel`, and `StatusBarFooter`. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures common behavior for JavaFX UI components.

The `UI` component uses the JavaFX UI framework. The layout of these UI parts is defined in corresponding `.fxml` files located in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml).

The `UI` component:

* executes user commands using the `Logic` component.
* listens for changes to the `Model` so that the UI can update automatically with the modified data.
* displays both a list of persons (`PersonListPanel`) and a list of visits (`VisitListPanel`), bound to the filtered and sorted lists provided by the `Model`.
* depends on classes in the `Model` component, as it displays `Person` and `Visit` objects stored in the `Model`.


### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `MedLoggerParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `MedLoggerParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `MedLoggerParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />

The `Model` component,

* stores the Med Logger data i.e., all `Person` and `Visit` objects (which are contained in `UniquePersonList` and `UniqueVisitList` respectively).
* stores the currently 'selected' `Person` and `Visit` objects (e.g., results of a search query) as separate _filtered_ lists which are exposed to outsiders as unmodifiable `ObservableList<Person>` and `ObservableList<Visit>` that can be observed e.g. the UI can be bound to these lists so that the UI automatically updates when the data changes.
* stores a `SortedList<Visit>` that wraps around the filtered visit list to enable dynamic sorting based on user commands, such as sorting visits by date in ascending or descending order.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` object.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `MedLogger`, which `Person` references. This allows `MedLogger` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both Med Logger data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `MedLoggerStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### AddVisit feature
![AddVisitCommand Sequence Diagram.png](images/AddVisitCommand%20Sequence%20Diagram.png)
#### Implementation

The current implementation of AddVisitCommand works as following: it will pass the visit to the model, and the model will then call
the addVisit command of Medlogger, which check if that visit belongs to someone already exists in Medlogger. The check is done using the NRIC of the person of visit,
if NRIC exists as key of hashmap, then the person already exists in Medlogger and NRIC is used to retrieve the person, then
add the visit to Medlogger. Otherwise a CommandException is throw, and reject adding the visit. 

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedMedLogger`. It extends `MedLogger` with an undo/redo history, stored internally as an `medLoggerStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedMedLogger#commit()` — Saves the current Med Logger state in its history.
* `VersionedMedLogger#undo()` — Restores the previous Med Logger state from its history.
* `VersionedMedLogger#redo()` — Restores a previously undone Med Logger state from its history.

These operations are exposed in the `Model` interface as `Model#commitMedLogger()`, `Model#undoMedLogger()` and `Model#redoMedLogger()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedMedLogger` will be initialized with the initial Med Logger state, and the `currentStatePointer` pointing to that single Med Logger state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the Med Logger. The `delete` command calls `Model#commitMedLogger()`, causing the modified state of the Med Logger after the `delete 5` command executes to be saved in the `MedLoggerStateList`, and the `currentStatePointer` is shifted to the newly inserted Med Logger state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitMedLogger()`, causing another modified Med Logger state to be saved into the `MedLoggerStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitMedLogger()`, so the Med Logger state will not be saved into the `MedLoggerStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoMedLogger()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous Med Logger state, and restores the Med Logger to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial MedLogger state, then there are no previous MedLogger states to restore. The `undo` command uses `Model#canUndoMedLogger()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redoMedLogger()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the Med Logger to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `MedLoggerStateList.size() - 1`, pointing to the latest Med Logger state, then there are no undone MedLogger states to restore. The `redo` command uses `Model#canRedoMedLogger()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the Med Logger, such as `list`, will usually not call `Model#commitMedLogger()`, `Model#undoMedLogger()` or `Model#redoMedLogger()`. Thus, the `MedLoggerStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitMedLogger()`. Since the `currentStatePointer` is not pointing at the end of the `MedLoggerStateList`, all Med Logger states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire Med Logger.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

### SortVisits feature
![SortVisitsCommand Sequence Diagram](images/DG_sortvisits.png)
<puml src="diagrams/SortVisitsDiagram.puml" width="500" />

#### Implementation

The `SortVisitsCommand` allows users to sort the current visit list based on the visit date and time. It supports both ascending and descending order.

This command uses a comparator to sort the list of visits by comparing the `DateTime` field. Internally, it calls the `model.sortFilteredVisitList(comparator)` method, which performs the sorting and updates the predicate of the `FilteredList` to reflect the new sorted order.

Due to the immutability of JavaFX’s `FilteredList`, direct sorting is not allowed. Instead, the list is copied and sorted separately, and then the model updates the predicate so that the UI reflects the sorted order.

The command uses the following logic:
- If `isDescending` is `false`, the comparator sorts in natural ascending order.
- If `isDescending` is `true`, the comparator is reversed.

The visit panel (`VisitListPanel`) will reflect the sorted order immediately as it is bound to the filtered list.

### ListVisits Feature

The `listvisits` command displays a list of visit records currently stored in the MedLogger. Users can optionally limit the number of visits displayed using the `l/` prefix or filter by NRIC using `n/`.

#### Implementation

The implementation follows a standard command-parsing-execution pipeline:

![ListVisitsCommand Sequence Diagram](images/DG_listvisits.png)
<puml src="diagrams/ListVisitsDiagram.puml" width="500" />

1. User inputs the `listvisits` command.
2. The `MedLoggerParser` routes the command to the `ListVisitsCommandParser`, which optionally parses prefixes like `l/` (limit) and `n/` (NRIC).
3. A `ListVisitsCommand` is constructed with the parsed arguments and executed.
4. It updates the filtered visit list in `ModelManager` using a custom predicate.
5. The UI observes the updated list and reflects the changes immediately.

This design reuses the filtered list mechanism also used for persons, allowing seamless display updates via JavaFX binding.


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Doctors or clinic assistants managing patient visit records in small private clinics
* Prefer fast, lightweight tools over complex, slow hospital systems
* Comfortable using command-line interfaces
* Need quick access to patient records during consultations
* Prefer keyboard-based workflows to minimize disruptions during work

**Value proposition**:  
A command-line tool for efficiently logging and retrieving patient visit records, symptoms, and treatments, enabling faster access to patient histories and smoother clinic operations compared to traditional GUI-based systems.

---

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​           | I want to …​                                               | So that I can…​                                           |
|----------|-------------------|-------------------------------------------------------------|-----------------------------------------------------------|
| `* * *` | doctor            | create a patient visit entry                                | log patient visits quickly                                |
| `* * *` | doctor            | record visit dateTime and time                                 | track when each visit happened                            |
| `* * *` | doctor            | record symptoms and treatments for a visit                 | document medical details accurately                       |
| `* * *` | doctor            | delete patient visit records                               | correct mistakes                                          |
| `* * *` | doctor            | view all patient visit information                         | review patient history                                    |
| `* *`   | doctor            | retrieve a patient’s visit history                         | make informed treatment decisions                         |
| `* *`   | doctor            | retrieve today’s patients                                  | check which patients I haven't seen                      |
| `* *`   | doctor            | filter records based on severity                           | focus on critical cases                                   |
| `* *`   | doctor            | filter patient records by symptoms                         | identify patterns in medical history                      |
| `* *`   | doctor            | sort patient records by dateTime                               | find recent visits easily                                 |
| `* *`   | doctor            | track patients who received specific medication            | ensure proper follow-up care                              |
| `* *`   | clinic assistant  | edit patient records                                       | keep information accurate                                 |
| `* *`   | clinic assistant  | generate a summary of a patient’s visit history            | prepare reports for doctors                               |
| `* *`   | clinic assistant  | search for patients by partial name matches                | find records quickly                                      |
| `* *`   | clinic assistant  | export patient visit records to CSV                        | share reports with external healthcare providers          |
| `* *`   | clinic manager    | view all patients seen by a specific doctor                | evaluate doctor performance                               |
| `*`     | clinic assistant  | sort visit dates                                           | schedule follow-ups easily                                |
| `*`     | doctor            | track recurring symptoms                                   | detect chronic conditions                                 |
| `*`     | clinic assistant  | create backup copies of records                            | protect against data loss                                 |
| `*`     | clinic assistant  | update patient contact information                         | maintain communication accuracy                           |
| `*`     | clinic assistant  | view multiple patient records simultaneously               | compare patient statuses                                  |
| `*`     | clinic assistant  | log patient complaints about visits                        | help the clinic improve service                           |
| `*`     | doctor            | set reminders for follow-up visits                         | ensure patients return as needed                          |
| `*`     | doctor            | undo the previously executed command                       | correct recent actions easily                             |

---

### Use cases

(For all use cases below, the **System** is the `MedLogger` and the **Actor** is the user unless specified otherwise)

---

**Use case: Add a symptom to a patient's visit**

**MSS (Main Success Scenario)**  
1. User requests to list visit records  
2. MedLogger displays a list of visit records  
3. User selects a visit by index and inputs the symptom  
4. MedLogger adds the symptom to the visit record  

Use case ends.

**Extensions**  
* 2a. The visit list is empty.  
  - Use case ends.  
* 3a. The selected index is invalid.  
  - 3a1. MedLogger shows an error message.  
  - Use case resumes at step 2.

---

**Use case: Generate a summary of a patient’s visit history**

**MSS (Main Success Scenario)**  
1. User searches for a patient by name  
2. MedLogger shows a list of matching patient records  
3. User selects the correct patient  
4. MedLogger generates and displays a summary of visit history  

Use case ends.

**Extensions**  
* 1a. No matching patients found.  
  - Use case ends.

---

### Non-Functional Requirements

1. Should work on any mainstream OS with Java 17 or higher installed.
2. Should handle up to 1000 patient records without noticeable performance degradation.
3. Typing commands should be faster than using a mouse for users with average typing speed.
4. Every modification to patient records should be logged with a timestamp, user ID, and action performed.
5. System response time should be under 1 second for all typical operations.
6. Should handle simultaneous access by multiple users without data corruption (future consideration).

---

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Visit record**: A log entry containing details of a patient's consultation, symptoms, and treatments
* **Symptom**: A medical complaint or issue reported by the patient
* **Medication**: Any prescribed drug or treatment recorded in the system
* **CSV**: Comma-Separated Values, a file format used for exporting data
* **CLI**: Command-Line Interface, a text-based way to interact with the software
* **Partial name match**: A search that returns results even if only part of a patient’s name is entered


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_

### Visiting a patient

1. Adding a visit to a person

   1. Prerequisites: Person must exist in the contact list with valid NRIC.

   1. Test case: `visit n/John Doe i/S1234567A r/Headache d/2025-04-04 10:00`  
      Expected: Visit is added. Confirmation message is shown.

   1. Test case: `visit n/John Doe i/S1234567A r/Back pain`  
      Expected: Visit is added with current date and time. Confirmation message is shown.

   1. Test case: `visit n/Someone i/S0000000X r/Cough`  
      Expected: Error message shown — person does not exist.

---

### Listing visits

1. Listing all visits

   1. Test case: `listvisits`  
      Expected: All visits are shown in the visit panel.

   1. Test case: `listvisits l/3`  
      Expected: Top 3 visits are shown.

   1. Test case: `listvisits n/S1234567A`  
      Expected: All visits related to that NRIC are shown.

   1. Test case: `listvisits n/S1234567A l/2`  
      Expected: 2 most recent visits of the patient are shown.

---

### Sorting visits

1. Sorting visits by date

   1. Test case: `sortvisits`  
      Expected: Visit list sorted in ascending date-time order.

   1. Test case: `sortvisits desc`  
      Expected: Visit list sorted in descending date-time order.

---

### Editing a visit

1. Editing visit information

   1. Prerequisites: `listvisits` must be used beforehand.

   1. Test case: `editvisit 1 r/Updated remark`  
      Expected: The remark of the first visit in the list is updated.

   1. Test case: `editvisit 1 d/2025-04-10 14:00`  
      Expected: Visit date is updated.

---

### Clearing visit panel

1. Clearing the visit list panel

   1. Test case: `clearvisits`  
      Expected: Visit panel is cleared.
