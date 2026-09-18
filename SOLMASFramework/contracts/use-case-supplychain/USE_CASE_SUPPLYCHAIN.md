# Supply Chain Use Case Overview

This document organises and describes the actors, roles, and responsibilities
for the `use-case-supplychain` contracts in the SOLMASFramework.  It is
intended to complement the Solidity code by capturing the high‑level
logistics architecture that the smart contracts support.

---

## 1. Primary Actors (The Agent Collective)

### 📌 Central Coordinator Agent ("The Brain")
- **Type:** Software Agent (Orchestrator)
- **Role:** Central intelligence for planning, optimisation and crisis
  management across the entire fleet.
- **Responsibilities:**
  1. *Global Planning* – ingests all delivery requests (retailer,
     wholesaler, consumer).
  2. *Resource Allocation* – matches packages to vehicles considering size,
     proximity, battery/fuel and capabilities.
  3. *Real‑Time Monitoring* – tracks status, location and ETA of every
     vehicle.
  4. *Dynamic Re‑optimisation* – recalculates assignments when field agents
     report delays.
  5. *Stakeholder Communication* – notifies retailers, wholesalers,
     consumers of delays or windows.
  6. *Fleet Management* – schedules charging/maintenance for vehicles.
  7. *Billing Coordination* – logs deliveries for invoicing commercial actors.
- **Goal:** maximise fleet efficiency and on‑time delivery rates across the
  supply chain.

### 🚁 Drone Agent (Aerial Vehicle Agent)
- **Type:** Embedded Software Agent (per drone)
- **Role:** Autonomous aerial delivery unit with local decision‑making.
- **Responsibilities:**
  - Local perception (wind, obstacles, weather, no‑fly zones).
  - Executes routes assigned by Coordinator.
  - Detects anomalies; performs local re‑planning for safety.
  - Communicates status/location/delays to Coordinator.
  - Handles pickup from retail/wholesale sites and final drop‑off to consumers.
- **Goal:** complete assigned delivery safely and efficiently.

### 🤖 Ground Robot Agent
- **Type:** Embedded Software Agent (per ground robot)
- **Role:** Autonomous ground delivery for sidewalks and streets.
- **Responsibilities:**
  - Local perception (sidewalks, crosswalks, obstacles).
  - Adheres to traffic rules while executing paths.
  - Adapts routes for closures or heavy traffic.
  - Accepts mid‑route assignments from Coordinator.
  - Picks up packages from commercial locations; delivers to consumers or
    relays to other agents.
  - Interfaces securely with loading docks and delivery locations.
- **Goal:** reliable navigation through urban terrain to fulfil deliveries.

---

## 2. Commercial Actors (Supply Chain Participants)

### 🏬 Retailer (Local store, restaurant, pharmacy)
- **Type:** Business entity.
- **Role:** Point of sale and originator of outbound shipments.
- **Responsibilities:**
  - Generate delivery requests on consumer purchase.
  - Package, label, and secure goods for transport.
  - Provide agent pickup points (shelves, lockers, loading zones).
  - Maintain inventory data for multi‑order optimisation.
  - Pay for delivery services or bundle cost into pricing.
- **Agent Interactions:**
  - *To Coordinator:* submit delivery requests (package details, destination,
    promised window) via API.
  - *To Fleet Agents:* hand off packages physically; scan QR codes for pickup
    confirmation.
  - *From Coordinator:* receive ETAs, confirmations, exception alerts.
- **Goal:** ensure fast, reliable customer deliveries to encourage repeat
  business.

### 🚚 Wholesaler / Distributor
- **Type:** Business entity.
- **Role:** Bulk supplier to retailers and businesses (B2B logistics).
- **Responsibilities:**
  - Ship large quantities to multiple retailer sites.
  - Manage complex schedules with strict deadlines (e.g., pre‑opening
    supplies).
  - Coordinate with retailers’ receiving windows.
  - Handle reverse logistics (returns, unsold goods).
- **Agent Interactions:**
  - *To Coordinator:* submit bulk manifests with stops, windows, access
    instructions.
  - *To Fleet Agents:* coordinate pallet/drone handoff with loading dock systems.
  - *From Coordinator:* receive proof‑of‑delivery for billing and inventory
    reconciliation.
- **Goal:** optimise B2B logistics efficiency and maintain retailer relations.

### 👤 Consumer (Individual)
- **Type:** Human end‑recipient.
- **Role:** Last‑mile delivery target.
- **Responsibilities:**
  - Place orders triggering delivery requests.
  - Provide accurate delivery location and access instructions.
  - Retrieve packages upon delivery.
  - Rate delivery experience (feedback loop).
- **Agent Interactions:**
  - *To Retailer/Platform:* place orders (indirectly sends delivery request).
  - *From Coordinator:* receive ETAs, delay notifications, confirmations.
  - *To/From Fleet Agents:* interact for package handoff; may supply codes or
    biometric verification for secure release.
- **Goal:** convenient, predictable, undamaged receipt of purchases.

---

## 3. Secondary Actors (Human Operations)

### 🛠️ Fleet Operations Manager
- **Type:** Human.
- **Role:** Strategic supervisor and exception handler.
- **Responsibilities:**
  - Set high‑level policies (e.g. prioritise medical deliveries).
  - Monitor fleet health and performance metrics.
  - Handle edge cases (damages, stuck vehicles).
  - Coordinate with municipal authorities on regulations.
  - Manage commercial relationships and review SLAs.
- **Goal:** ensure safe, profitable, compliant fleet operations.

### 🏪 Retail Store Manager/Staff
- **Type:** Human.
- **Role:** Local contact for package pickup.
- **Responsibilities:**
  - Prepare outgoing packages for agent pickup.
  - Place items in agent‑accessible locations.
  - Handle exceptions when agents cannot complete pickup.
  - Coordinate with drivers for large wholesaler deliveries.
- **Agent Interaction:** physical interaction with robots/drones as required.

> _Note:_ the consumer is already described in Commercial Actors but also
functions as a human secondary actor when interacting directly with agents.

---

## 4. Tertiary Actors (Supporting Systems & Data Sources)

A variety of software systems provide data or services to the Coordinator and
fleet agents:

- **E‑Commerce / Order Management Platforms** – aggregate consumer orders and
  route delivery requests (e.g., Shopify, Amazon).
- **Retailer Inventory Management Systems** – supply real‑time stock levels
  and product locations for readiness checks.
- **Wholesaler Warehouse Management Systems (WMS)** – coordinate pallet
  positions, dock availability, and shipment windows.
- **Urban Airspace Management** – regulate low‑altitude drone traffic and
  provide no‑fly/altitude restrictions.
- **Traffic & Navigation Services** – used by ground robots for routing.
- **Weather Service APIs** – feed environmental conditions to Coordinator and
  Drone Agents.
- **Package Management System** – repository of package details (size, weight,
  contents, handling requirements).
- **Customer Notification System** – sends SMS/email/app notifications on
  behalf of the Coordinator.
- **Billing & Settlement System** – processes invoices based on delivery logs
  from the Coordinator.
- **Charging/Maintenance Station Network** – interfaces used by vehicles to
  schedule recharge or service.

---

## 5. External Authorities (Regulatory & Environmental)

- **City Municipality / Local Government** – issues permits, defines delivery
  zones, manages public infrastructure.
- **Law Enforcement / Emergency Services** – establish dynamic no‑fly or
  restricted zones during incidents.
- **The Public (Bystanders)** – share the environment; their acceptance and
  safety are key to system success.

---

This overview provides a foundation for thinking about how the Solidity
`use-case-supplychain` contracts relate to a broader autonomous delivery
ecosystem. Future extensions could implement smart‑contract versions of the
Coordinator, vehicle agents, and interaction protocols described above.