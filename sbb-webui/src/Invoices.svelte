<script>
    let invoices = []
    fetch('/api/invoices')
        .then((response) => response.json())
        .then((data) => invoices = data)
</script>

<div>
    {#each invoices as invoice}
        <li>
            {invoice.id} - {invoice.amount}
        </li>
    {/each}
</div>