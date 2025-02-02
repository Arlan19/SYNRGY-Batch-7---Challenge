PGDMP      3                |         
   challenge3    16.2    16.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16777 
   challenge3    DATABASE     �   CREATE DATABASE challenge3 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Indonesia.1252';
    DROP DATABASE challenge3;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    16785    merchant    TABLE     �   CREATE TABLE public.merchant (
    id uuid NOT NULL,
    merchant_name character varying(255),
    merchant_location character varying(255),
    open boolean
);
    DROP TABLE public.merchant;
       public         heap    postgres    false    4            �            1259    16814    order_detail    TABLE     �   CREATE TABLE public.order_detail (
    id uuid NOT NULL,
    order_id uuid,
    product_id uuid,
    quantity integer,
    total_price numeric
);
     DROP TABLE public.order_detail;
       public         heap    postgres    false    4            �            1259    16804    orders    TABLE     �   CREATE TABLE public.orders (
    id uuid NOT NULL,
    order_time timestamp without time zone,
    destination_address character varying(255),
    user_id uuid,
    completed boolean
);
    DROP TABLE public.orders;
       public         heap    postgres    false    4            �            1259    16792    product    TABLE     �   CREATE TABLE public.product (
    id uuid NOT NULL,
    product_name character varying(255),
    price numeric,
    merchant_id uuid
);
    DROP TABLE public.product;
       public         heap    postgres    false    4            �            1259    16778    users    TABLE     �   CREATE TABLE public.users (
    id uuid NOT NULL,
    username character varying(255),
    email_address character varying(255),
    password character varying(255)
);
    DROP TABLE public.users;
       public         heap    postgres    false    4            �          0    16785    merchant 
   TABLE DATA           N   COPY public.merchant (id, merchant_name, merchant_location, open) FROM stdin;
    public          postgres    false    216   �       �          0    16814    order_detail 
   TABLE DATA           W   COPY public.order_detail (id, order_id, product_id, quantity, total_price) FROM stdin;
    public          postgres    false    219   �       �          0    16804    orders 
   TABLE DATA           Y   COPY public.orders (id, order_time, destination_address, user_id, completed) FROM stdin;
    public          postgres    false    218   �       �          0    16792    product 
   TABLE DATA           G   COPY public.product (id, product_name, price, merchant_id) FROM stdin;
    public          postgres    false    217   �       �          0    16778    users 
   TABLE DATA           F   COPY public.users (id, username, email_address, password) FROM stdin;
    public          postgres    false    215   �       ,           2606    16791    merchant merchant_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.merchant
    ADD CONSTRAINT merchant_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.merchant DROP CONSTRAINT merchant_pkey;
       public            postgres    false    216            2           2606    16820    order_detail order_detail_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT order_detail_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.order_detail DROP CONSTRAINT order_detail_pkey;
       public            postgres    false    219            0           2606    16808    orders orders_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pkey;
       public            postgres    false    218            .           2606    16798    product product_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public            postgres    false    217            *           2606    16784    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    215            5           2606    16821 '   order_detail order_detail_order_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT order_detail_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(id);
 Q   ALTER TABLE ONLY public.order_detail DROP CONSTRAINT order_detail_order_id_fkey;
       public          postgres    false    218    4656    219            6           2606    16826 )   order_detail order_detail_product_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT order_detail_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id);
 S   ALTER TABLE ONLY public.order_detail DROP CONSTRAINT order_detail_product_id_fkey;
       public          postgres    false    219    4654    217            4           2606    16809    orders orders_user_id_fkey    FK CONSTRAINT     y   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 D   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_user_id_fkey;
       public          postgres    false    215    218    4650            3           2606    16799     product product_merchant_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchant(id);
 J   ALTER TABLE ONLY public.product DROP CONSTRAINT product_merchant_id_fkey;
       public          postgres    false    217    216    4652            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     