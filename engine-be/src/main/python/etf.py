import justetf_scraping


df = justetf_scraping.load_overview(enrich=True)

filtered_df = df[
    (df['age_in_years'] > 5) &
    (df['size'] > 1000) &
    df['asset_class'].notnull() &
    (df['asset_class'].str.strip() != "") &
    df['region'].notnull() &
    (df['region'].str.strip() != "")
]

df_reset = df.reset_index()
selected_columns = ['isin', 'name', 'asset_class','region','last_three_years_volatility', 'strategy']
result_df = df_reset[selected_columns]
result_df = result_df.dropna()

header = ['Isin', 'Name', 'Asset', 'Region', 'Volatility', 'Strategy']
result_df.to_csv('etf.csv', index=False, header=header)
