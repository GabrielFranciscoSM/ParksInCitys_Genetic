  # Parks in Citys — Genetic Algorithm for Urban Park Placement

  Parks in Citys is a university final project (AI course) created during an Erasmus stay in Salerno, Italy. The project explores procedural city generation and applies a Genetic Algorithm (GA) to optimize placement and sizing of parks inside a synthetic city map.

  The goal: starting from procedurally-generated cities (buildings, roads, free spaces), evolve city variants where parks are placed to improve a combination of citizen coverage (value) and economic cost (money).

  ---

  ## Highlights

  - Procedural city generator (roads, buildings, neighborhoods).
  - Individuals are complete city maps (tile grids) with parks added.
  - Multiple initialization strategies for parks (random, close-to-buildings, spread-based expansion).
  - Modular GA design: selection, crossover, and several mutation operators.
  - Fitness composed of a weighted combination of a value function (citizen coverage) and a money/cost function.

  ---

  ## Project structure (important files)

  - `src/parksincity_geneticalgorithms/ParksInCity_GeneticAlgorithms.java` — main launcher that opens the GUI.
  - `src/Model/ParksInCityGA.java` — core GA controller (initializer, selection, crossover, mutation, fitness loop).
  - `src/Model/CityParameters.java` — city generation parameters (size, densities, neighborhood size, etc.).
  - `src/Model/ModelParameters.java` — GA parameters (mutation prob, crossover prob, repetitions, etc.).
  - `src/Model/Individuals/CityTileset.java` — the Individual implementation: a 2D grid of tiles (park, building, road, void) and helper methods for modifying/valuing parks.
  - `src/Model/Individuals/Tiles/` — tile types (ParkTile, BuildingTile, RoadTile, VoidTile, etc.).
  - `src/Model/Inicializer/` — procedural initializers for cities and parks (e.g. `RandomCityInicializer`, `RandomParkInicializer`).
  - `src/Model/operators/` — crossover (`NeighborhoodCrossover`, `TilesCrossover`), selection (`RankSelection`, `KWayTournamentSelection`), and mutation operators (`RandomParkMutation`, `PointNeighborhoodMutation`, `ParkExpansionMutation`, ...).
  - `src/Model/fitness/` — fitness components: `ValueFunction`, `MoneyFunction`, and combined `PonderatedFunction`.
  - `src/Views/GUI/` — a Swing-based GUI for configuring and running the GA (MainWindow, StartView, CityView).

  ---

  ## How it works (conceptual)

  1. A `CityTileset` represents a candidate city: a grid of Tiles grouped into neighborhoods. Each tile has a type and values (e.g., how many citizens a park would cover).
  2. A procedural initializer generates roads and buildings, and computes possible park values for void tiles.
  3. The GA (`ParksInCityGA`) creates an initial population of `CityTileset` individuals where parks are placed by an initializer.
  4. Each generation the algorithm:
     - Evaluates fitness using a ponderated function: fitness = w_value * ValueFunction + w_money * MoneyFunction.
     - Applies selection to keep/breed better individuals.
     - Applies crossover operators (neighborhood-aware and tile-based) to recombine maps.
     - Applies a set of mutation operators (random parks, point/neighborhood mutations, park expansion).
  5. The population evolves toward maps that balance citizen coverage and economic cost.

  ---

  ## Main algorithms & operators

  - Initialization:
    - `RandomCityInicializer` — procedural node-based roads and building placement, then computes void tile park-values.
    - `RandomParkInicializer`, `CloseToBuildingsParkInicializer` — different strategies to seed parks.

  - Fitness:
    - `ValueFunction` — measures citizens served by parks (coverage/benefit).
    - `MoneyFunction` — estimates economic cost for building parks.
    - `PonderatedFunction` — combines both with configurable weight.

  - Crossover:
    - `NeighborhoodCrossover` — swaps neighborhoods between parents to preserve local structure.
    - `TilesCrossover` / `GeometricalCrossover` — tile-level recombination options.

  - Mutation:
    - Random park insertion, neighborhood-targeted mutations, park expansion (grow parks locally), and point mutations inside neighborhoods.

  - Selection:
    - Rank and K-way tournament selection controllers are implemented and configurable.

  ---

  ## Configuration

  - City settings: edit or instantiate `CityParameters` (size, road/building densities, park spreadness, neighborhood size).
  - GA settings: edit or instantiate `ModelParameters` (population size, mutation prob, crossover prob/intensity, repetitions limit, fitness weights).
  - The GUI exposes many of these options for experimentation.

  ---

  ## Running the project

  Recommended: open the project in NetBeans (this repository contains NetBeans metadata and `build.xml`) and Run the project. The GUI entry point is `parksincity_geneticalgorithms.ParksInCity_GeneticAlgorithms`.

  Command-line / Ant (NetBeans-style):

  1. Install Apache Ant (if not already installed).
  2. From the repository root run:

  ```bash
  ant clean
  ant
  ant run   # If your Ant build script provides a run target (NetBeans projects usually do)
  ```

  After a NetBeans build the runnable jar is typically placed in `dist/` and can be launched with:

  ```bash
  java -jar dist/ParksInCitys_Genetic.jar
  ```

  If you prefer to run from your IDE: import as a Java project and run `ParksInCity_GeneticAlgorithms.main()`.

  ---

  ## Quick experiments and tips

  - Try different balance weights in `ModelParameters` to prioritize either value (citizen coverage) or money (cost).
  - Change `CityParameters.DEFAULTSIZE` to reduce map size for faster experiments (e.g., 50 or 100) while tuning operators.
  - Use low population + fewer repetitions for fast prototyping, then scale up for final runs.

  ---

  ## Extending the project

  - Add new fitness components (e.g., accessibility, biodiversity, walking distance metrics).
  - Add more realistic road/building generators or import real city layouts.
  - Replace the GUI with an interactive web frontend or export runs as images/CSV for analysis.

  ---

  ## Credits & acknowledgements

  Author: Gabriel (Erasmus, Salerno) — final project for an AI course.

  This repository contains an academic project exploring genetic algorithms and procedural generation. Feel free to reuse or adapt the code for research and learning (please credit the original author when used).

  ---

  License: MIT (suggested). Add a LICENSE file if you want an explicit open-source license.
