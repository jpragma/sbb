<script>
    // populated with data from the endpoint
    export let invoices;
</script>

<h1>Number of invoices {invoices.length}</h1>

<table border="1">
    <tr>
        <th>id</th>
        <th>client</th>
    </tr>
    {#each invoices as invoice}
    <tr>
        <td>{invoice.id}</td>
        <td>{invoice.client}</td>
    </tr>
    {/each}
</table>
