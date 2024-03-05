--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2024-01-22 11:31:40

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 24688)
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    id bigint NOT NULL,
    username character varying,
    name character varying
);


ALTER TABLE public.client OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 24695)
-- Name: flight; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flight (
    id bigint NOT NULL,
    departuretime timestamp without time zone,
    landingtime timestamp without time zone,
    "from" character varying,
    "to" character varying,
    seats integer
);


ALTER TABLE public.flight OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24726)
-- Name: ticket; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ticket (
    id bigint NOT NULL,
    id_flight bigint,
    id_client bigint,
    username character varying,
    purchasetime timestamp without time zone
);


ALTER TABLE public.ticket OWNER TO postgres;

--
-- TOC entry 4792 (class 0 OID 24688)
-- Dependencies: 215
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.client (id, username, name) FROM stdin;
1	user1	name1
2	user2	name2
3	user3	name3
4	user4	name4
5	user5	name5
\.


--
-- TOC entry 4793 (class 0 OID 24695)
-- Dependencies: 216
-- Data for Name: flight; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flight (id, departuretime, landingtime, "from", "to", seats) FROM stdin;
1	2024-01-22 08:45:10	2024-01-23 08:45:20	Oradea	Bucuresti	200
2	2024-01-25 08:45:50	2024-01-26 08:45:50	Iasi	Cluj	250
3	2024-02-09 08:46:15	2024-02-09 08:46:30	Oradea	Budapesta	230
\.


--
-- TOC entry 4794 (class 0 OID 24726)
-- Dependencies: 217
-- Data for Name: ticket; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ticket (id, id_flight, id_client, username, purchasetime) FROM stdin;
1	1	1	user1	2024-01-22 08:55:45
2	2	1	user1	2024-01-23 09:56:12
3	1	2	user2	2024-01-23 08:56:30
4	3	1	user1	2024-01-24 09:57:37
5	1	3	user3	2024-01-24 09:57:59
5402	3	2	user2	2024-01-22 10:15:02
413	2	3	user3	2024-01-22 10:24:16
8120	1	4	user4	2024-01-22 10:48:25
2633	1	5	user5	2024-01-22 11:20:03
\.


--
-- TOC entry 4642 (class 2606 OID 24694)
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- TOC entry 4644 (class 2606 OID 24701)
-- Name: flight flight_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flight
    ADD CONSTRAINT flight_pkey PRIMARY KEY (id);


--
-- TOC entry 4646 (class 2606 OID 24734)
-- Name: ticket ticket_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_pkey PRIMARY KEY (id) INCLUDE (id);


--
-- TOC entry 4647 (class 2606 OID 24740)
-- Name: ticket ticket_id_client_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_id_client_fkey FOREIGN KEY (id_client) REFERENCES public.client(id) NOT VALID;


--
-- TOC entry 4648 (class 2606 OID 24735)
-- Name: ticket ticket_id_flight_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_id_flight_fkey FOREIGN KEY (id_flight) REFERENCES public.flight(id) NOT VALID;


-- Completed on 2024-01-22 11:31:41

--
-- PostgreSQL database dump complete
--

