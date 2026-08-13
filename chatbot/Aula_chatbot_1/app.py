import streamlit as st
from langchain_groq import ChatGroq
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import StrOutputParser
from langchain_core.messages import AIMessage, HumanMessage
from dotenv import load_dotenv

load_dotenv()

st.set_page_config(
    page_title="TechStore Assistente 🤖",
    page_icon="💻"
)

st.title("💻 TechStore - Assistente Virtual")

id_modelo = "llama-3.3-70b-versatile"


def load_llm():
    return ChatGroq(
        model=id_modelo,
        temperature=0.3,
        max_retries=2
    )


llm = load_llm()