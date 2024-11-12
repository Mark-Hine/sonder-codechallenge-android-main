# **Sonder Code Challenge**

## **Overview**
The goal is to implement a **Search Functionality** using a multi-module **Clean Architecture** setup, incorporating **Flow** and **View State Management**.

## **Project Structure**
- **Modules:**
  - `App`
  - `Data`
  - `Common`
  
- **Mock Data**: Provided in the `Data` module; no network**** requests are required.
- **UI Setup**: Basic UI is pre-configured with:
  - `MainActivity` and `MainFragment`
  - XML layouts for UI components:
    - **SearchView with Toolbar**
    - **Title TextView**
    - **ProgressBar**
    - **RecyclerViews**
    - Predefined item layouts for each view type.
  
- **Optional**: You can switch to **Jetpack Compose** if needed (please notify for extra time).

---

## **Implementation Requirements**
1. **Repository:**
   - Implement the `SearchRepository` interface and modify it as necessary.
   - Use **UseCases** for data exchange between `Repository` and `ViewModels`.

2. **ViewModels:**
   - Implement state management logic.
   - Implement data logic using UseCases.

3. **Flow:**
   - Use `Flow` for data exchange. 

4. **Unit Testing:**
   - Add unit tests for the ViewModels.

---

## **Improvement Suggestions**
- If you identify areas for improvement in the existing code, feel free to make the changes.
- **Document your changes** using comments in the code for easy review.

---

## **Development Guidelines**
1. **Project Setup:**
   - The project should compile on **Android Studio Koala | 2024.1.2 Patch 1**.

2. **Version Control:**
   - Upload the completed project to your preferred version control platform (e.g., GitHub, Bitbucket).
   - Use **branches** to organize your tasks and **merge** them into the main branch incrementally.

3. **Expected Behavior:**
   - A **video demo** and **screenshots** are provided for reference.
   - Follow the UI and functional flow shown in the video.

4. **Need help?**
   - Feel free to email us for any questions.
---

## **Functional Requirements**   
1. **Search Functionality:**
   - **Search Query**: `"mock"`
     - Searching for `"mock"` should load results.
     - This is pre-configured in the mock data.
   
   - **Error Handling**:
     - Use `"error"` as the query to simulate an error scenario.
     - This is also pre-configured in the mock data.

2. **UI Behavior:**
   - **Initial State:**
     - When the app launches, the title should display: `"Search our comprehensive library of resources"`
     - **Progress Bar** should be invisible.
     
   - **On Search Initiation**:
     - Title should display: `"Searching for ‘{search query}’"`
     - **Progress Bar** should be visible.
     - Since there are no network requests, simulate a network call with a delay of **2 seconds**.
   
   - **On Results Loaded**:
     - Title: `"Showing results for ‘{search query}’"`
     - **Progress Bar** should disappear.
     - Display the results in the following **chronological order**:
       1. **Horizontal Compact**
       2. **Vertical Compact**
       3. **Horizontal Detailed**
       4. **Vertical Detailed**

5. **Mock Data Details:**
   - Mock data is provided in two main files:
     - **`MockRequests`**: Contains all required request objects.
     - **`MockResults`**: Contains the corresponding results.

   - **Request Object Structure**:
     ```kotlin
     data class SearchRequestParams(
       val sectionTitle: String,
       val query: String,
       val size: Int = 10,
       val contentTypes: List<String>
     )
     ```

   - **Result Object Structure**:
     ```kotlin
     data class SearchSectionResult(
       val sectionTitle: String,
       val items: List<SearchItem>
     )
     ```

   - **Individual Search Item Object Structure**:
     ```kotlin
     data class SearchItem(
       val id: String? = null,
       val title: String? = null,
       val description: String? = null,
       val contentType: SearchContentType? = null,
       val thumbnail: String? = null,
       val action: SearchAction? = null
     )
     ```
     - `contentType` is the an enum which defines what type of item it is.
     - Use this to filter out unwanted items from a section. The section should only show items that are accepted by it.

   - **Action Handling**:
     ```kotlin
     data class SearchAction(
        val scheme: String,
        val title: String? = null,
        val type: SearchActionType
     )
     ```
     - Each item has a unique `SearchAction`.
     - `title` is the title of the action.
     - `type` is a way to determine how the scheme should be handled.
     - If `scheme` is a **URL**, open it in the browser or a WebView.
     - If `scheme` is just **text**, show a Toast message with the scheme data.

---

## **Edge Cases and Error Handling**
1. **Empty Sections:**
   - If a section has no data, hide it from the UI.
   - Example: `getCategoriesSectionSearchResults()` always returns an empty list for the **Vertical Compact** section. This section should never be visible.

2. **All Sections Empty:**
   - Title should show an error message: `"We apologise, but it seems like we’re facing a temporary hiccup loading search results."`
   - **Progress Bar** should be invisible.
   - Use the mock functions `getErrorRequestParams()` and `getErrorResult()` for this scenario.

3. **Clearing Search Query:**
   - If the user clears the search query (via the **X** button or by erasing with keyboard):
     - Reset the UI to the **Initial State**.
     - Previous search results should be cleared.

4. **App Lifecycle:**
   - Consider the app’s lifecycle (e.g., **background/foreground** transitions).
   - Handle state retention or resetting as necessary.

---

## **Submission**
1. Push your completed project to a new repository on your preferred platform.
2. Share the **repository link** with us.
3. Use **branches** and **merging** for version control (e.g., separate task branches).

---
