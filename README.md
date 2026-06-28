# HIS_Fhir_Converter
AI-driven FHIR Mapping Assistant

This tool helps hospital IT personnel and medical coders map legacy hospital data structures and local codes into standard HL7 FHIR resources through an interactive, AI-powered conversational interface with a strict Human-in-the-loop workflow.

## Tech Stack
* **Backend:** Java Spring Boot 3.x, Spring WebFlux, Spring Data JPA, LangChain4j, HAPI FHIR.
* **Frontend:** React 18+ (Vite), TailwindCSS, Zustand.
* **Databases:** PostgreSQL (Relational), Qdrant (Vector).
* **AI:** Local Ollama support / Google Vertex AI (Gemini).

## Launch Process

The application is fully containerized. You can run the entire stack locally using Docker Compose.

1. **Clone the repository:**
   ```bash
   git clone <repo-url>
   cd HIS_Fhir_Converter
   ```

2. **Configure Environment Variables:**
   Copy the example environment file and configure it as needed.
   ```bash
   cp .env.example .env
   ```
   *Note: If you are using the `cloud` profile, ensure you add your `GOOGLE_VERTEX_AI_KEY` to the `.env` file.*

3. **Start the Application:**
   Run the following command to build and spin up all containers (Postgres, Qdrant, Backend, and Frontend):
   ```bash
   docker compose up -d --build
   ```

4. **Access the Application:**
   * **Frontend Dashboard:** Open your browser and navigate to `http://localhost`
   * **Backend API:** Available at `http://localhost:8080/api`

## How to Use

The application is designed around a strict "Human-in-the-Loop" workflow.

1. **Upload Schema (Chat Panel):**
   Use the left Chat Panel to talk to the AI Assistant. Upload your source data schema (e.g., SQL DDL, CSV headers, or JSON samples) and describe what you want to map.

2. **AI Proposal:**
   The AI will analyze your local schema and propose mappings to standard HL7 FHIR resources (R4/R5 or specific IGs like TW Core).

3. **Review & Staging (Right Top Panel):**
   Proposed mappings will appear in the "Pending Mappings" staging area on the top right. Here you can:
   * **Modify:** Tweak the source-to-target alignment.
   * **Approve:** Explicitly approve the mapping. Approved mappings are saved to the PostgreSQL database for future reference and generation.

4. **Target Structure Visualization (Right Bottom Panel):**
   As you map fields, the "Target FHIR Structure" panel provides a collapsible tree view of the final FHIR JSON payload, giving you immediate visual feedback on how your local data fits into the standard FHIR hierarchy.
