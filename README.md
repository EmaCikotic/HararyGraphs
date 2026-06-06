# On the Infection Number of Harary Graphs

This project investigates the infection number of Harary graphs under the threshold-2 infection model.

## Infection Number

The infection number of a graph $G$ is defined as

$$
m_2(G)=\min { |S| : S \text{ is contagious} }.
$$

A vertex becomes infected whenever at least two of its neighbours are infected.

## Main Results

For the cycle case:

$$
m_2(H_{2,n})=\left\lceil \frac{n}{2} \right\rceil
$$

For cubic Harary graphs:

$$
m_2(H_{3,n})=\left\lfloor \frac{n}{4} \right\rfloor + 1
$$

For higher-degree Harary graphs:

$$
m_2(H_{k,n})=2, \qquad k \ge 4
$$

## Project Structure

```text
src/main/java/org/example
├── Harary.java
├── HararyGraph.java
├── InfectionSimulator.java
└── Pair.java
```

## Compilation

```bash
javac src/main/java/org/example/*.java
```

## Execution

```bash
java -cp src/main/java org.example.Harary
```

## Report

The complete report is available in the repository and contains the theoretical background, proofs, computational results, and conclusions.
